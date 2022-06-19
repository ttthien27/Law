package com.android.Law.models;

public class DocumentNew extends Document{
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public DocumentNew(String docId, String docUrl, String docTitle, String docDescription, String date) {
        super(docId, docUrl, docTitle, docDescription);
        this.date = date;
    }
}
