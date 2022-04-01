package com.android.Law.models;

public class Document {

    private String docId;
    private String docTitle;
    private String docDescription;

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocId() {
        return docId;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public Document(String docId, String docTitle, String docDescription) {
        this.docId = docId;
        this.docTitle = docTitle;
        this.docDescription = docDescription;
    }
}
