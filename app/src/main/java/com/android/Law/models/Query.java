package com.android.Law.models;

import com.google.gson.annotations.SerializedName;

public class Query {
    @SerializedName("query")
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Query(String query) {
        this.query = query;
    }
}
