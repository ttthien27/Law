package com.android.Law.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.activity.CategoryActivity;
import com.android.Law.activity.DocumentViewActivity;
import com.android.Law.activity.LawTitleActivity;
import com.android.Law.activity.LoginActivity;
import com.android.Law.activity.MainActivity;
import com.android.Law.activity.MostViewActivity;
import com.android.Law.activity.NewDocumentActivity;
import com.android.Law.activity.SearchActivity;
import com.android.Law.activity.TestFirebaseActivity;
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
    private EditText edt_Search;
    private ImageButton btn_News;
    private ImageButton btn_Search;
    private ImageButton btn_More;
    private ImageButton btn_Support;
    private ImageButton btn_Category;
    private ImageButton btn_LatestDoc;
    private ImageButton btn_LawTitle;
    private Dialog dialog;

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
            //mContext = getContext();
            mActivity = getActivity();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rv_Home_Document);
        btn_LatestDoc = view.findViewById(R.id.imgBtn_Home_LatestDoc);
        btn_News = view.findViewById(R.id.imgBtn_Home_News);
        btn_More = view.findViewById(R.id.imgBtn_Home_More);
        btn_Support = view.findViewById(R.id.imgBtn_Home_Support);
        btn_Search = view.findViewById(R.id.imgBtn_Home_Search);
        btn_Category = view.findViewById(R.id.imgBtn_Home_Category);
        btn_LawTitle = view.findViewById(R.id.imgBtn_Home_LawTitle);
        edt_Search = view.findViewById(R.id.edt_Home_Search);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        documentAdapter =new DocumentAdapter(mContext,getListDocument());
        recyclerView.setAdapter(documentAdapter);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        btn_LatestDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNews();
            }
        });

        btn_News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNews();
            }
        });

        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_Search.getText().toString().length()!=0)
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

        btn_LawTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {btnLawTitle(); }
        });

        return view;
    }

    private void btnNews(){
        //Intent intent = new Intent(getActivity(),LoginActivity.class);
        //startActivity(intent);

        /*dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = dialog.findViewById(R.id.btn_Cancel_Dialog);


        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Okay", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        dialog.show();*/

        Intent intent = new Intent(getActivity(), NewDocumentActivity.class);
        startActivity(intent);
    }

    private void btnSearch(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("index",2);
        intent.putExtra("query",edt_Search.getText().toString());
        startActivity(intent);
    }

    private void btnMore(){
        //Intent intent = new Intent(getActivity(), MostViewActivity.class);
        Intent intent = new Intent(getActivity(), TestFirebaseActivity.class);
        startActivity(intent);
    }

    private void btnSupport(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("index",1);
        startActivity(intent);
    }

    private void btnCategory(){
        Intent intent = new Intent(getActivity(), CategoryActivity.class);
        startActivity(intent);
    }

    private void btnLawTitle(){
        Intent intent = new Intent(getActivity(), LawTitleActivity.class);
        startActivity(intent);
    }

    private List<Document> getListDocument() {

        List<Document> list = new ArrayList<>();
        list.add(new Document("99990","https://thuvienphapluat.vn/van-ban/Thuong-mai/Thong-bao-176-TB-VPCP-2022-ket-luan-tai-buoi-lam-viec-voi-lanh-dao-Son-La-517695.aspx","THÔNG BÁO","KẾT LUẬN CỦA THỦ TƯỚNG CHÍNH PHỦ PHẠM MINH CHÍNH TẠI BUỔI LÀM VIỆC VỚI LÃNH ĐẠO TỈNH SƠN LA"));
        list.add(new Document("99991","https://thuvienphapluat.vn/van-ban/The-thao-Y-te/Quyet-dinh-1577-QD-BYT-2022-Ke-hoach-thuc-hien-Quyet-dinh-2215-QD-TTg-517660.aspx","QUYẾT ĐỊNH","PHÊ DUYỆT KẾ HOẠCH TRIỂN KHAI THỰC HIỆN QUYẾT ĐỊNH SỐ 2215/QĐ-TTG NGÀY 28/12/2021 CỦA THỦ TƯỚNG CHÍNH PHỦ VỀ VIỆC BAN HÀNH KẾ HOẠCH HÀNH ĐỘNG QUỐC GIA KHẮC PHỤC HẬU QUẢ CHẤT ĐỘC HÓA HỌC/DIOXIN SAU CHIẾN TRANH Ở VIỆT NAM CỦA BỘ Y TẾ GIAI ĐOẠN 2022-2030"));
        list.add(new Document("99992","https://thuvienphapluat.vn/van-ban/The-thao-Y-te/Quyet-dinh-1571-QD-BYT-2022-tai-lieu-huong-dan-theo-doi-do-me-an-than-bang-dien-nao-so-hoa-517631.aspx","QUYẾT ĐỊNH","VỀ VIỆC BAN HÀNH TÀI LIỆU “HƯỚNG DẪN QUY TRÌNH KỸ THUẬT THEO DÕI ĐỘ MÊ, AN THẦN TRONG GÂY MÊ HỒI SỨC VÀ HỒI SỨC TÍCH CỰC BẰNG ĐIỆN NÃO SỐ HÓA (BAO GỒM BIS, ENTROPY VÀ CÁC THIẾT BỊ CÓ CÔNG DỤNG TƯƠNG ĐƯƠNG)”"));
        list.add(new Document("99993","https://thuvienphapluat.vn/van-ban/Bo-may-hanh-chinh/Quyet-dinh-1310-QD-BTNMT-2022-cong-bo-thu-tuc-hanh-chinh-dia-chat-va-khoang-san-517615.aspx","QUYẾT ĐỊNH","VỀ VIỆC CÔNG BỐ THỦ TỤC HÀNH CHÍNH BỊ BÃI BỎ TRONG LĨNH VỰC ĐỊA CHẤT VÀ KHOÁNG SẢN THUỘC PHẠM VI CHỨC NĂNG QUẢN LÝ CỦA BỘ TÀI NGUYÊN VÀ MÔI TRƯỜNG"));
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