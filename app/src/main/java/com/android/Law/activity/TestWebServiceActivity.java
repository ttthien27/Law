package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.api.ApiService;
import com.android.Law.models.Cate;
import com.android.Law.models.Document;
import com.android.Law.models.Query;
import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestWebServiceActivity extends AppCompatActivity {

    Button btngetlist;
    ListView lvcatalog;
    TextView textCount;
    final String URL = "http://testwebservicethien.somee.com/WebService1.asmx?WSDL";
    ArrayList<String> arrCate = new ArrayList();
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web_service);
        StrictMode.ThreadPolicy policy = (new Builder()).permitAll().build();
        StrictMode.setThreadPolicy(policy);
        lvcatalog = findViewById(R.id.lvcatalog);
        btngetlist = findViewById(R.id.btnlistcatalog);
        textCount = findViewById(R.id.txtcount);
        this.btngetlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //TestWebServiceActivity.this.doGetList();
                //clickCallApi();
                clickCallApishowDocument();
            }
        });
        //this.adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, this.arrCate);
        //this.lvcatalog.setAdapter(this.adapter);
    }

    private void clickCallApi(){
        ApiService.apiService.search("thiên@tai@tại@việt@nam").enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                List<Document> listdocument = (List<Document>) response.body();
                for (Document d:listdocument) {
                    Log.d("----TestService----", "String document: "+d.getDocDescription());
                }
                Log.d("DataCheck",new Gson().toJson(response.body()));
                Log.d("----TestService----", "onResponse: Success");
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                Log.d("----TestService----", "onFailure: Fail" + t.toString());
            }
        });
    }

    private void clickCallApishowDocument(){
        Query query = new Query("Thiên tai Việt Nam");
        ApiService.apiService.searchDocument(query).enqueue(new Callback<List<Document>>() {
            @Override
            public void onResponse(Call<List<Document>> call, Response<List<Document>> response) {
                List<Document> listdocument = (List<Document>) response.body();
                for (Document d:listdocument) {
                    Log.d("----TestService_showDocument----", "String document: "+d.getDocDescription());
                }
                Log.d("DataCheck",new Gson().toJson(response.body()));
                Log.d("----TestService_showDocument----", "onResponse: Success");
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
            }
        });
    }

}