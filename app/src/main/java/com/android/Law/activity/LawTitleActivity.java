package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.adapter.DocumentAdapter;
import com.android.Law.adapter.DocumentSearchAdapter;
import com.android.Law.adapter.LawTitleAdapter;
import com.android.Law.api.ApiService;
import com.android.Law.models.Document;
import com.android.Law.models.Query;
import com.android.Law.spinner.CustomSpinner;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LawTitleActivity extends AppCompatActivity implements CustomSpinner.OnSpinnerEventsListener{

    private CustomSpinner spinnerLawTitle;
    private LawTitleAdapter lawTitleAdapter;
    private ProgressBar progressBar;
    private List<Document> listDocumentLawTitle;
    private DocumentAdapter documentAdapter_search;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LawTitleActivity.this);
    private String mess_receive = "";
    private RecyclerView recyclerViewLawTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_title);

        recyclerViewLawTitle = findViewById(R.id.rv_LawTitle);
        spinnerLawTitle = findViewById(R.id.spinner_LawTitle);
        progressBar = findViewById(R.id.proB_LawTitle);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewLawTitle.setLayoutManager(linearLayoutManager);

        spinnerLawTitle.setSpinnerEventsListener(LawTitleActivity.this);
        List<String> listLawTitle = new ArrayList<String>();
        listLawTitle.add("Thông tư");
        listLawTitle.add("Nghị định");
        listLawTitle.add("Nghị quyết");
        listLawTitle.add("Chỉ thị");

        lawTitleAdapter = new LawTitleAdapter(LawTitleActivity.this, listLawTitle);
        spinnerLawTitle.setAdapter(lawTitleAdapter);
        spinnerLawTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(LawTitleActivity.this, lawTitleAdapter.getItem(position).toString(),Toast.LENGTH_SHORT).show();
                clickCallAPIShowDocument(lawTitleAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        spinnerLawTitle.setBackground(getResources().getDrawable(R.drawable.bg_spinner_lawtitle_up));
    }


    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        spinnerLawTitle.setBackground(getResources().getDrawable(R.drawable.bg_spinner_lawtitle));
    }

    private void clickCallAPIShowDocument(String strQuery){
        progressBar.setVisibility(View.VISIBLE);
        Query query = new Query(strQuery);
        Log.d("Activity", "--------------: 3");
        ApiService.apiService.searchDocumentTitle(query).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                Log.d("Activity", "--------------: 4");
                listDocumentLawTitle = (List<Document>) response.body();
                for (Document d: listDocumentLawTitle) {
                    Log.d("----TestService_showDocument----", "String document: "+d.getDocDescription());
                }
                Log.d("DataCheck",new Gson().toJson(response.body()));
                Log.d("----TestService_showDocument----", "onResponse: Success");
                mess_receive = "Success";
                Log.d("Activity", "--------------: 5");
                documentAdapter_search = new DocumentAdapter(LawTitleActivity.this,listDocumentLawTitle);
                Log.d("Activity", "--------------: 6");
                recyclerViewLawTitle.setAdapter(documentAdapter_search);
                Log.d("Activity", "--------------: 7");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
                clickCallAPIShowDocument(strQuery);
            }

        });
    }
}