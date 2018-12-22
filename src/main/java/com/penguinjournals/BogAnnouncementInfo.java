package com.penguinjournals;

public class BogAnnouncementInfo {
    private String bogNumber;
    private String bogDate;
    private String sectionNumber;
    private String sectionDescription;
    private String agency;
    private String department;
    private String rawBody;
    private String htmlBody;

    public BogAnnouncementInfo(String bogNumber, String bogDate, String sectionNumber, String sectionDescription, String agency, String department, String rawBody, String htmlBody) {
        this.bogNumber = bogNumber;
        this.bogDate = bogDate;
        this.sectionNumber = sectionNumber;
        this.sectionDescription = sectionDescription;
        this.agency = agency;
        this.department = department;
        this.rawBody = rawBody;
        this.htmlBody = htmlBody;
    }
}
