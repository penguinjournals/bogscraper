package com.penguinjournals;

import com.google.gson.Gson;

import java.time.LocalDate;

public class JsonBasedInfoPiece {
    private LocalDate date;
    private Gson info;
    private String raw;

    public JsonBasedInfoPiece(final LocalDate date, final String info, final String raw) {
        this.date = date;
        this.info = info;
        this.raw = raw;
    }

    public void store() {
        PostgreStorer postgreStorer = new PostgreStorer();
        postgreStorer.storeInfoPiece(this);
    }

    public LocalDate getDate() { return date; }

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
