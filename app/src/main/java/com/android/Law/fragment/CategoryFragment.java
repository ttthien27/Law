package com.android.Law.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.Law.R;
import com.android.Law.activity.SearchActivity;
import com.android.Law.adapter.DocumentAdapter;
import com.android.Law.api.ApiService;
import com.android.Law.models.Document;
import com.android.Law.models.Query;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Query query;
    private Context mContext;
    private Activity mActivity;
    private RecyclerView recyclerView_Category;
    private DocumentAdapter documentAdapter_Category;
    private ProgressBar progressBar;
    List<Document> list = new ArrayList<Document>();
    String mess_receive = "";

    public CategoryFragment() {
        // Required empty public constructor
    }

    public CategoryFragment(Query q) {
        // Required empty public constructor
        query=q;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
            mContext = getContext();
            mActivity = getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        progressBar = view.findViewById(R.id.proB_category);
        progressBar.setVisibility(View.GONE);

        recyclerView_Category = view.findViewById(R.id.rv_category);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView_Category.setLayoutManager(linearLayoutManager);
        clickCallApishowDocument();
        return view;
    }

    private void clickCallApishowDocument(){
        //query = new Query(editText.getText().toString());
        Log.d("Activity", "--------------: 3");
        progressBar.setVisibility(View.VISIBLE);
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
                documentAdapter_Category = new DocumentAdapter(list);
                Log.d("Activity", "--------------: 6");
                recyclerView_Category.setAdapter(documentAdapter_Category);
                Log.d("Activity", "--------------: 7");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
                progressBar.setVisibility(View.VISIBLE);
            }

        });
    }
}