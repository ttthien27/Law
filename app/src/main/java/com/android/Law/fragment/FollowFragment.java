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
 * Use the {@link FollowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowFragment extends Fragment {

    private Context mContext;
    private Activity mActivity;
    private RecyclerView recyclerView_follow;
    private DocumentAdapter documentAdapter_follow;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowFragment newInstance(String param1, String param2) {
        FollowFragment fragment = new FollowFragment();
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
        //return inflater.inflate(R.layout.fragment_follow, container, false);
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        recyclerView_follow = view.findViewById(R.id.rv_document_follow);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView_follow.setLayoutManager(linearLayoutManager);
        documentAdapter_follow =new DocumentAdapter(getListDocument());
        recyclerView_follow.setAdapter(documentAdapter_follow);

        return view;
    }

    private List<Document> getListDocument() {

        List<Document> list = new ArrayList<>();
        list.add(new Document("1","NGH??? ?????NH","Quy???t ?????nh 24/Q??-UBQGC??S n??m 2022 Quy ch??? ho???t ?????ng c???a ???y ban Qu???c gia v??? chuy???n ?????i s???"));
        list.add(new Document("2","NGH??? ?????NH","Quy???t ?????nh 24/Q??-UBQGC??S n??m 2022 Quy ch??? ho???t ?????ng c???a ???y ban Qu???c gia v??? chuy???n ?????i s???"));
        list.add(new Document("3","NGH??? ?????NH","Quy???t ?????nh 24/Q??-UBQGC??S n??m 2022 Quy ch??? ho???t ?????ng c???a ???y ban Qu???c gia v??? chuy???n ?????i s???"));
        list.add(new Document("4","NGH??? ?????NH","Quy???t ?????nh 24/Q??-UBQGC??S n??m 2022 Quy ch??? ho???t ?????ng c???a ???y ban Qu???c gia v??? chuy???n ?????i s???"));
        return list;
    }
}