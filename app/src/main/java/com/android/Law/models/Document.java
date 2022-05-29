package com.android.Law.models;

import java.io.Serializable;

public class Document implements Serializable {

    private String docId;
    private String docUrl;
    private String docTitle;
    private String docDescription;
    private String docParagraphShort;
    private String docTermQuery;

    public String getDocParagraphShort() { return docParagraphShort; }

    public void setDocParagraphShort(String docParagraphShort) { this.docParagraphShort = docParagraphShort; }

    public String getDocTermQuery() { return docTermQuery; }

    public void setDocTermQuery(String docTermQuery) { this.docTermQuery = docTermQuery; }

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

    public String getDocUrl() {return docUrl; }

    public void setDocUrl(String docUrl) {this.docUrl = docUrl; }

    public Document() {

    }

    public Document(String docId, String docUrl, String docTitle, String docDescription) {
        this.docId = docId;
        this.docUrl =docUrl;
        this.docTitle = docTitle;
        this.docDescription = docDescription;
    }

    public Document(String docId, String docTitle, String docDescription) {
        this.docId = docId;
        this.docUrl =docUrl;
        this.docTitle = docTitle;
        this.docDescription = docDescription;
    }

    public Document(String docId, String docUrl, String docTitle, String docDescription, String docParagraphShort, String docTermQuery) {
        this.docId = docId;
        this.docUrl = docUrl;
        this.docTitle = docTitle;
        this.docDescription = docDescription;
        this.docParagraphShort = docParagraphShort;
        this.docTermQuery = docTermQuery;
    }
}
