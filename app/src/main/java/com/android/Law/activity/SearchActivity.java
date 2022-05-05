package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.adapter.DocumentAdapter;
import com.android.Law.adapter.DocumentSearchAdapter;
import com.android.Law.api.ApiService;
import com.android.Law.models.Document;
import com.android.Law.models.Query;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerSearch;
    private ProgressBar progressBar;
    ImageButton btnSearch;
    DocumentSearchAdapter documentAdapter_search;
    EditText editText;

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
    List<Document> list = new ArrayList<Document>();
    Query query;
    String messenge = "";
    String mess_receive = "";
    ArrayList<String> arrayList;
    private static String ip = "192.168.1.112";
    private static Socket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.edittext_Search_Search);
        btnSearch = findViewById(R.id.imgBtn_Search_Search);
        recyclerSearch = findViewById(R.id.rv_search_follow);
        progressBar = findViewById(R.id.proB_search);
        progressBar.setVisibility(View.GONE);
        recyclerSearch.setLayoutManager(linearLayoutManager);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length()!=0){

                    mess_receive = "";
                    //send_text(v);
                    Log.d("Activity", "--------------: 2");
                    /*boolean ktr = false;
                    while (!ktr) {
                        if (mess_receive.length() != 0) {
                            Log.d("Activity", "--------------: mess -"+mess_receive);
                            ktr = true;
                        }
                        Log.d("Activity", "--------------: mess -"+mess_receive);
                        Log.d("Activity", "--------------: đang chờ");
                    }*/
                    //list = getListDocument(arrayS);


                    clickCallAPIShowDocument();

                }
            }
        });
    }

    private void clickCallAPIShowDocument(){
        progressBar.setVisibility(View.VISIBLE);
        query = new Query(editText.getText().toString());
        Log.d("Activity", "--------------: 3");
        ApiService.apiService.showDocument(query).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                Log.d("Activity", "--------------: 4");
                list = (List<Document>) response.body();
                for (Document d: list) {
                    Log.d("----TestService_showDocument----", "String document: "+d.getDocDescription());
                }
                Log.d("DataCheck",new Gson().toJson(response.body()));
                Log.d("----TestService_showDocument----", "onResponse: Success");
                mess_receive = "Success";
                Log.d("Activity", "--------------: 5");
                documentAdapter_search = new DocumentSearchAdapter(list);
                Log.d("Activity", "--------------: 6");
                recyclerSearch.setAdapter(documentAdapter_search);
                Log.d("Activity", "--------------: 7");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
            }

        });
    }

    private List<Document> getListDocument(String[] arrayS) {
        query = new Query(editText.getText().toString());
        List<Document> list = new ArrayList<>();
        int i = 1;
        for (String s : arrayS) {
            list.add(new Document(String.valueOf(i), s, "NGHỊ ĐỊNH"));
            i++;
        }
        return list;
    }


    public void send_text(View view) {
        myAsyncTask_Send mt = new myAsyncTask_Send();
        mt.execute();
        Toast.makeText(getApplicationContext(), "Data send", Toast.LENGTH_LONG).show();
        Log.d("Activity", "--------------: 1");
    }


    class myAsyncTask_Send extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("Activity", "--------------: 3");
            ApiService.apiService.showDocument(query).enqueue(new Callback<List<Document>>() {
                @Override
                public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                    list = (List<Document>) response.body();
                    for (Document d:list) {
                        Log.d("----TestService_showDocument----", "String document: "+d.getDocDescription());
                    }
                    Log.d("DataCheck",new Gson().toJson(response.body()));
                    Log.d("----TestService_showDocument----", "onResponse: Success");
                    mess_receive = "Success";

                }

                @Override
                public void onFailure(Call<List<Document>> call, Throwable t) {
                    Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
                }
            });
            return null;
        }
    }

    public void cleardbView()
    {
        final Thread countdown = new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(8640000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            recyclerSearch = (RecyclerView) findViewById(R.id.rv_search_follow);
                            list.clear();
                            linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                            documentAdapter_search.notifyDataSetChanged();
                            return;
                        }
                    });

                } catch (InterruptedException e) {
                }
                return;
            }
        };
        countdown.start();
    }
}