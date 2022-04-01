package com.android.Law.models;

public class Cate {
    private String cateId;
    private String cateName;

    public String getCateId() {
        return this.cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return this.cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Cate(String cateId, String cateName) {
        this.cateId = cateId;
        this.cateName = cateName;
    }

    public String toString() {
        return this.cateId.trim() + " - " + this.cateName.trim();
    }
}
