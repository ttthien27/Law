package com.android.Law.models;

public class DocumentMostView extends Document{
    private int view;

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public DocumentMostView(String docId, String docUrl, String docTitle, String docDescription, int view) {
        super(docId, docUrl, docTitle, docDescription);
        this.view = view;
    }
}
