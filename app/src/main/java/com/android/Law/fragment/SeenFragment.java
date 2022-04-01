package com.android.Law.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.Law.R;
import com.android.Law.adapter.DocumentAdapter;
import com.android.Law.models.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeenFragment extends Fragment {

    private Context mContext;
    private Activity mActivity;
    private RecyclerView recyclerView_seen;
    private DocumentAdapter documentAdapter_seen;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SeenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeenFragment newInstance(String param1, String param2) {
        SeenFragment fragment = new SeenFragment();
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
        //return inflater.inflate(R.layout.fragment_seen, container, false);

        View view = inflater.inflate(R.layout.fragment_seen, container, false);
        recyclerView_seen = view.findViewById(R.id.rv_document_seen);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView_seen.setLayoutManager(linearLayoutManager);
        documentAdapter_seen =new DocumentAdapter(getListDocument());
        recyclerView_seen.setAdapter(documentAdapter_seen);

        return view;
    }

    private List<Document> getListDocument() {

        List<Document> list = new ArrayList<>();
        list.add(new Document("1","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("2","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("3","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("4","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("5","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("6","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("7","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("8","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("9","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("10","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("11","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        list.add(new Document("12","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số"));
        return list;
    }
}