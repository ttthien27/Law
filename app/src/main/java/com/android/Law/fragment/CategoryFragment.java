package com.android.Law.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
import com.google.android.material.chip.Chip;
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
    private String strListChip;
    private Context mContext;
    private Activity mActivity;
    private RecyclerView recyclerView_Category;
    private DocumentAdapter documentAdapter_Category;
    private ProgressBar progressBar;
    List<Document> list = new ArrayList<Document>();
    List<String> listChip = new ArrayList<String>();
    String mess_receive = "";

    public CategoryFragment() {
        // Required empty public constructor
    }

    public CategoryFragment(Query q,String s) {
        // Required empty public constructor
        query=q;strListChip=s;
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
            //mContext = getActivity().getApplicationContext();
            mActivity = getActivity();
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
            recyclerView_Category.setLayoutManager(linearLayoutManager);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        progressBar = view.findViewById(R.id.proB_category);
        progressBar.setVisibility(View.GONE);

        listChip.add(query.getQuery());

        String[] str = strListChip.split("@");
        Chip chip1,chip2,chip3,chip4,chip5,chip6;
        chip1=view.findViewById(R.id.chip1);chip1.setText(str[0]);
        chip2=view.findViewById(R.id.chip2);chip2.setText(str[1]);
        chip3=view.findViewById(R.id.chip3);chip3.setText(str[2]);
        chip4=view.findViewById(R.id.chip4);chip4.setText(str[3]);
        chip5=view.findViewById(R.id.chip5);chip5.setText(str[4]);
        chip6=view.findViewById(R.id.chip6);chip6.setText(str[5]);



        recyclerView_Category = view.findViewById(R.id.rv_category);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView_Category.setLayoutManager(linearLayoutManager);
        recyclerView_Category.setAdapter(documentAdapter_Category);
        list=null;
        documentAdapter_Category = new DocumentAdapter(mContext,list);
        Log.d("CategoryFragment", "onCreateView Category: null");
        recyclerView_Category.setAdapter(documentAdapter_Category);
        clickCallAPIShowDocument(query);

        chip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip1.setCheckable(true);
                changeListChip(str[0]);
                if(chip1.getChipBackgroundColor()==ColorStateList.valueOf(Color.parseColor("#ffcd66"))){
                    chip1.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
                    clickCallAPIShowDocument(getQueryToListChip());
                }else{
                    chip1.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffcd66")));
                    Log.d("CategoryFragment", "onClick: true");
                    clickCallAPIShowDocument(getQueryToListChip());
                }
                Log.d("FragmentCategory", "onClickChip: "+getStringToListChip());
            }
        });

        chip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip2.setCheckable(true);
                changeListChip(str[1]);
                if(chip2.getChipBackgroundColor()==ColorStateList.valueOf(Color.parseColor("#ffcd66"))){
                    chip2.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
                    clickCallAPIShowDocument(getQueryToListChip());
                }else{
                    chip2.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffcd66")));
                    Log.d("CategoryFragment", "onClick: true");
                    clickCallAPIShowDocument(getQueryToListChip());}

                Log.d("FragmentCategory", "onClickChip: "+getStringToListChip());
            }
        });

        chip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip3.setCheckable(true);
                changeListChip(str[2]);
                if(chip3.getChipBackgroundColor()==ColorStateList.valueOf(Color.parseColor("#ffcd66"))){
                    chip3.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
                    clickCallAPIShowDocument(getQueryToListChip());
                }else{
                    chip3.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffcd66")));
                    Log.d("CategoryFragment", "onClick: true");
                    clickCallAPIShowDocument(getQueryToListChip());}

                Log.d("FragmentCategory", "onClickChip: "+getStringToListChip());
            }
        });

        chip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip4.setCheckable(true);
                changeListChip(str[3]);
                if(chip4.getChipBackgroundColor()==ColorStateList.valueOf(Color.parseColor("#ffcd66"))){
                    chip4.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
                    clickCallAPIShowDocument(getQueryToListChip());
                }else{chip4.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffcd66")));
                    Log.d("CategoryFragment", "onClick: true");
                    clickCallAPIShowDocument(getQueryToListChip());}

                Log.d("FragmentCategory", "onClickChip: "+getStringToListChip());
            }
        });

        chip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip5.setCheckable(true);
                changeListChip(str[4]);
                if(chip5.getChipBackgroundColor()==ColorStateList.valueOf(Color.parseColor("#ffcd66"))){
                    chip5.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
                    clickCallAPIShowDocument(getQueryToListChip());
                }else{
                    chip5.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffcd66")));
                    Log.d("CategoryFragment", "onClick: true");
                    clickCallAPIShowDocument(getQueryToListChip());}

                Log.d("FragmentCategory", "onClickChip: "+getStringToListChip());
            }
        });

        chip6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip6.setCheckable(true);
                changeListChip(str[5]);
                if(chip6.getChipBackgroundColor()==ColorStateList.valueOf(Color.parseColor("#ffcd66"))){
                    chip6.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#e5e5e5")));
                    clickCallAPIShowDocument(getQueryToListChip());
                }else{chip6.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffcd66")));
                    Log.d("CategoryFragment", "onClick: true");
                    clickCallAPIShowDocument(getQueryToListChip());}

                Log.d("FragmentCategory", "onClickChip: "+getStringToListChip());
            }
        });



        return view;
    }

    private void clickCallAPIShowDocument(Query q){
        //query = new Query(editText.getText().toString());
        Log.d("Activity", "--------------: 3");
        progressBar.setVisibility(View.VISIBLE);
        ApiService.apiService.searchDocumentCategory(q).enqueue(new Callback<List<Document>>() {
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
                documentAdapter_Category = new DocumentAdapter(mContext,list);
                Log.d("Activity", "--------------: 6");
                recyclerView_Category.setAdapter(documentAdapter_Category);
                //documentAdapter_Category.notifyDataSetChanged();
                Log.d("Activity", "--------------: 7");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Document>> call, Throwable t) {
                Log.d("----TestService_showDocument----", "onFailure: Fail" + t.toString());
                progressBar.setVisibility(View.VISIBLE);
                clickCallAPIShowDocument(query);
            }

        });
    }

    private void changeListChip(String s){
        boolean check = false;
        for (String str:listChip) {
            if(str.equals(s))
                check = true;
            }
        if(check!=true){listChip.add(s);}else {listChip.remove(s);}
    }

    private String getStringToListChip(){
        String s="";
        if(listChip.isEmpty()) return "";
        for (String str:listChip) {
            s=s+str+" ";
        }
        return s.substring(0,s.length()-1);
    }

    private Query getQueryToListChip(){
        Query queryTerm = new Query();
        String str = "";
        for (String s:listChip) {
            str = str + s +" ";
        }
        queryTerm.setQuery(str.substring(0,str.length()-1));
        return queryTerm;
    }

}