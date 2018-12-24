package com.penguinjournals;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BogScraper {
    private static final Logger LOG = LoggerFactory.getLogger(BogScraper.class);
    private static final Integer MAXIMUM_SCRAP_TIMEOUT = 30000;

    /**
     * https://egoitza.gipuzkoa.eus/gao-bog/castell/bog/2018/12/13/bc181213.htm
     */
    private static final String BASE_URL = "https://egoitza.gipuzkoa.eus";
    private static final String DAILY_BOG_PATH = "/gao-bog/castell/bog/2018/12/13/bc181213.htm";

    public void scrap() {
        LocalDate dateToScrap = LocalDate.now();
        LOG.info("Scraping data for: " + dateToScrap);
        String dailyBogUrl = BASE_URL + DAILY_BOG_PATH;
        Document dailyBogAnnoucement = null;
        try {
            dailyBogAnnoucement = Jsoup.connect(dailyBogUrl).timeout(MAXIMUM_SCRAP_TIMEOUT).get();
            List<String> announcements = retrieveAnnouncements(dailyBogAnnoucement);
            for (String announcementUrl : announcements) {
                LOG.info("Announcement URL:" + announcementUrl);
                parseAnnouncement(announcementUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseAnnouncement(final String bogAnnouncementUrl) {
        Document bogAnnouncement = null;
        try {
            bogAnnouncement = Jsoup.parse(new URL(bogAnnouncementUrl).openStream(), "Windows-1252", bogAnnouncementUrl,
                    Parser.htmlParser());
            LocalDate announcementDate = extractDateFromAnnouncement(bogAnnouncement);
            String announcementRawText = bogAnnouncement.toString();
            String announcementInfo = html2jsonBogAnnouncement(bogAnnouncement);
            InfoPiece infoPiece = new InfoPiece(announcementDate,
                                                announcementInfo,
                                                announcementRawText);
            infoPiece.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LocalDate extractDateFromAnnouncement(final Document bogAnnouncement) {
        Locale locale = new Locale("es", "ES");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, 'a' dd 'de' MMMM 'de' yyyy", locale);
        String bogDate = bogAnnouncement.getElementById("fecha").text().toLowerCase();
        LocalDate localDate = LocalDate.parse(bogDate, dateTimeFormatter);
        return localDate;
    }

    private String html2jsonBogAnnouncement(final Document bogAnnouncement) {
        BogAnnouncementInfo announcementInfo = null;
        String announcementType = bogAnnouncement.getElementById("nro_seccion").text();
        String[] norma000announcementAgencies = {"3", "6", "7", "8"};
        if (Arrays.stream(norma000announcementAgencies).anyMatch(announcementType::equals)) {
            announcementInfo = parseNorma000Announcement(bogAnnouncement);
        } else {
            announcementInfo = parseNorma00Announcement(bogAnnouncement);
        }
        Gson gson = new Gson();
        return gson.toJson(announcementInfo);
    }

    private BogAnnouncementInfo parseNorma000Announcement(final Document bogAnnouncement) {
        String agencyClassName = "norma_000";
        String departmentClassName = "norma_000";
        return parseAnnouncement(bogAnnouncement, agencyClassName, departmentClassName);
    }

    private BogAnnouncementInfo parseNorma00Announcement(final Document bogAnnouncement) {
        String agencyClassName = "norma00";
        String departmentClassName = "norma01";
        return parseAnnouncement(bogAnnouncement, agencyClassName, departmentClassName);
    }

    private BogAnnouncementInfo parseAnnouncement(final Document bogAnnouncement, final String agencyClassName, final String departmentClassName) {
        String bogNumber = bogAnnouncement.getElementById("nro_boletin").text();
        String bogDate = bogAnnouncement.getElementById("fecha").text();
        String sectionNumber = bogAnnouncement.getElementById("nro_seccion").text();
        String sectionDescription = bogAnnouncement.getElementById("desc_seccion").text();
        String agency = bogAnnouncement.getElementsByClass(agencyClassName).get(0).text();
        String department = bogAnnouncement.getElementsByClass(departmentClassName).get(0).text();
        String rawBody = bogAnnouncement.getElementById("cuerpoAnuncio").text();
        String htmlBody = bogAnnouncement.getElementById("cuerpoAnuncio").outerHtml();

        BogAnnouncementInfo announcement = new BogAnnouncementInfo(bogDate,
                bogNumber,
                sectionNumber,
                sectionDescription,
                agency,
                department,
                rawBody,
                htmlBody);

        return announcement;
    }

    private List<String> retrieveAnnouncements(final Document dailyBogAnnoucement) {
        Elements followableLinks = dailyBogAnnoucement.getElementsByClass("descarga_html");
        List<String> announcements = new ArrayList<String>();
        for (Element link : followableLinks) {
            announcements.add(link.attr("abs:href"));
        }
        return announcements;
    }
}
