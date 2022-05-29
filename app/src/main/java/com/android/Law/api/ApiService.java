package com.android.Law.api;

import com.android.Law.models.Document;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://lucene-app-webservice.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("search")
    Call<List<Document>> search(@Query("query") String query);

    @POST("searchDocument")
    Call<List<Document>> searchDocument(@Body com.android.Law.models.Query query);

    @POST("searchDocumentCategory")
    Call<List<Document>> searchDocumentCategory(@Body com.android.Law.models.Query query);

    @POST("searchDocumentTitle")
    Call<List<Document>> searchDocumentTitle(@Body com.android.Law.models.Query query);
}
