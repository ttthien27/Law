package com.android.Law.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.activity.SearchActivity;
import com.android.Law.adapter.DocumentSearchAdapter;
import com.android.Law.api.ApiService;
import com.android.Law.models.Document;
import com.android.Law.models.Query;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerSearch;
    private ProgressBar progressBar;
    private Context mContext;
    private Activity mActivity;
    private Query query;
    private ImageButton btnSearch;
    private DocumentSearchAdapter documentAdapter_search;
    private EditText edt_Search;
    private LinearLayoutManager linearLayoutManager;
    private List<Document> list = new ArrayList<Document>();
    private String mess_receive = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    public SearchFragment(Query q) {
        this.query = q;
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        linearLayoutManager = new LinearLayoutManager(mContext);
        edt_Search = view.findViewById(R.id.edt_Search_Search);
        btnSearch = view.findViewById(R.id.imgBtn_Search);
        progressBar = view.findViewById(R.id.proB_Search);
        progressBar.setVisibility(View.GONE);
        recyclerSearch = view.findViewById(R.id.rv_Search_Document);
        recyclerSearch.setLayoutManager(linearLayoutManager);

        Toolbar toolbar = view.findViewById(R.id.toolbar_Search);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        //Log.d("SearchFragment", "onCreateView: check query"+query.getQuery());
        if(query!=null){
            edt_Search.setText(query.getQuery());
            clickCallAPIShowDocumentToIntent();
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_Search.getText().toString().length()!=0){

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


        return view;
    }

    private void clickCallAPIShowDocument(){
        progressBar.setVisibility(View.VISIBLE);
        query = new Query(edt_Search.getText().toString());
        Log.d("Activity", "--------------: 3");
        ApiService.apiService.searchDocument(query).enqueue(new Callback<List<Document>>() {
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
                documentAdapter_search = new DocumentSearchAdapter(mContext, list);
                Log.d("Activity", "--------------: 6");
                recyclerSearch.setAdapter(documentAdapter_search);
                Log.d("Activity", "--------------: 7");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
                clickCallAPIShowDocument();
            }

        });
    }

    private void clickCallAPIShowDocumentToIntent(){
        progressBar.setVisibility(View.VISIBLE);
        Log.d("Activity", "--------------: 3");
        ApiService.apiService.searchDocument(query).enqueue(new Callback<List<Document>>() {
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
                documentAdapter_search = new DocumentSearchAdapter(mContext,list);
                Log.d("Activity", "--------------: 6");
                recyclerSearch.setAdapter(documentAdapter_search);
                Log.d("Activity", "--------------: 7");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
                clickCallAPIShowDocument();
            }

        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_lienhe:
                Toast.makeText(getActivity(),"Liên hệ",Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_about:
                Toast.makeText(getActivity(),"Về chúng tôi",Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_setting:
                Toast.makeText(getActivity(),"Cài đặt",Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}