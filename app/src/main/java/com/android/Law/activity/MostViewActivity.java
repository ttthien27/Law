package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.Law.R;
import com.android.Law.adapter.DocumentMostViewAdapter;
import com.android.Law.models.Document;
import com.android.Law.models.DocumentMostView;

import java.util.ArrayList;
import java.util.List;

public class MostViewActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMostView;
    private DocumentMostViewAdapter documentMostViewAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_view);

        recyclerViewMostView = findViewById(R.id.rv_MostView);
        progressBar = findViewById(R.id.proB_MostView);
        progressBar.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MostViewActivity.this);
        recyclerViewMostView.setLayoutManager(linearLayoutManager);
        documentMostViewAdapter = new DocumentMostViewAdapter(MostViewActivity.this,getListDocument());
        recyclerViewMostView.setAdapter(documentMostViewAdapter);

    }

    private List<DocumentMostView> getListDocument() {

        List<DocumentMostView> list = new ArrayList<>();
        list.add(new DocumentMostView("1", "dddddd", "NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",21));
        list.add(new DocumentMostView("2","dddddd","QUYẾT ĐỊNH","VỀ CHẾ ĐỘ PHỤ CẤP ĐỐI VỚI CÁN BỘ, CÔNG CHỨC ĐỘI Y TẾ DỰ PHÒNG TUYẾN QUẬN – HUYỆN VÀ TRẠM Y TẾ PHƯỜNG – XÃ, THỊ TRẤN",20));
        list.add(new DocumentMostView("3","dddddd","QUYẾT ĐỊNH","VỀ ĐIỀU CHỈNH BỔ SUNG QUYẾT ĐỊNH SỐ 05/2005/QĐ-UB NGÀY 17 THÁNG 01 NĂM 2005 CỦA ỦY BAN NHÂN DÂN THÀNH PHỐ BAN HÀNH ĐỊNH MỨC, ĐƠN GIÁ CHI PHÍ VẬN CHUYỂN KHÁCH CÔNG CỘNG BẰNG XE BUÝT VÀ XE ĐƯA RƯỚC HỌC SINH-SINH VIÊN VÀ CÔNG NHÂN TRÊN ĐỊA BÀN THÀNH PHỐ HỒ CHÍ MINH",18));
        list.add(new DocumentMostView("4","dddddd","QUYẾT ĐỊNH","VỀ BỔ SUNG DỰ TOÁN KINH PHÍ NĂM 2005 CHO 14 SỞ – NGÀNH THỰC HIỆN KHOÁN CHI",17));
        list.add(new DocumentMostView("5","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",10));
        list.add(new DocumentMostView("6","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",9));
        list.add(new DocumentMostView("7","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",8));
        list.add(new DocumentMostView("8","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",5));
        list.add(new DocumentMostView("9","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",5));
        list.add(new DocumentMostView("10","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",4));
        list.add(new DocumentMostView("11","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",3));
        list.add(new DocumentMostView("12","dddddd","NGHỊ ĐỊNH","Quyết định 24/QĐ-UBQGCĐS năm 2022 Quy chế hoạt động của Ủy ban Quốc gia về chuyển đổi số",2));
        return list;
    }
}