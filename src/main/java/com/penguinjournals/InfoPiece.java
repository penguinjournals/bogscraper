package com.penguinjournals;

import java.time.LocalDate;

public class InfoPiece {
    private LocalDate date;
    private String info;
    private String raw;

    public InfoPiece(final LocalDate date, final String info, final String raw) {
        this.date = date;
        this.info = info;
        this.raw = raw;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    public String getRaw() {
        return raw;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public void setRaw(final String raw) {
        this.raw = raw;
    }
}
