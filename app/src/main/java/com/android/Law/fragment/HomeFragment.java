package com.android.Law.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.activity.CategoryActivity;
import com.android.Law.activity.DocumentViewActivity;
import com.android.Law.activity.LoginActivity;
import com.android.Law.activity.MainActivity;
import com.android.Law.activity.SearchActivity;
import com.android.Law.activity.TestWebServiceActivity;
import com.android.Law.adapter.DocumentAdapter;
import com.android.Law.models.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private Context mContext;
    private Activity mActivity;
    private RecyclerView recyclerView;
    private DocumentAdapter documentAdapter;
    private ImageButton btn_News;
    private ImageButton btn_Search;
    private ImageButton btn_More;
    private ImageButton btn_Support;
    private ImageButton btn_Category;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_document);
        btn_News = view.findViewById(R.id.btn_Latest_Doc);
        btn_Search = view.findViewById(R.id.imgBtn_Home_search);
        btn_More = view.findViewById(R.id.imgBtn_Home_More);
        btn_Support = view.findViewById(R.id.imgBtn_Home_Support);
        btn_Category = view.findViewById(R.id.imgBtn_Home_category);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        documentAdapter =new DocumentAdapter(getListDocument());
        recyclerView.setAdapter(documentAdapter);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        btn_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNews();
            }
        });

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearch();
            }
        });

        btn_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMore();
            }
        });

        btn_Support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSupport();
            }
        });

        btn_Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {btnCategory(); }
        });

        return view;
    }

    private void btnNews(){
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
    }

    private void btnSearch(){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    private void btnMore(){
        Intent intent = new Intent(getActivity(), DocumentViewActivity.class);
        startActivity(intent);
    }

    private void btnSupport(){
        Intent intent = new Intent(getActivity(), TestWebServiceActivity.class);
        startActivity(intent);
    }

    private void btnCategory(){
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        startActivity(intent);
    }

    private List<Document> getListDocument() {

        List<Document> list = new ArrayList<>();
        list.add(new Document("1","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("2","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("3","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("4","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        return list;
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