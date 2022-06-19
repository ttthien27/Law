package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.Law.R;
import com.android.Law.adapter.DocumentAdapter;
import com.android.Law.adapter.DocumentNewAdapter;
import com.android.Law.models.Document;
import com.android.Law.models.DocumentNew;

import java.util.ArrayList;
import java.util.List;

public class NewDocumentActivity extends AppCompatActivity {

    private List<Document> listDocumentNewDocument;
    private DocumentNewAdapter documentAdapter_NewDocumet;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NewDocumentActivity.this);
    private String mess_receive = "";
    private RecyclerView recyclerViewNewDocument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_document);
        recyclerViewNewDocument = findViewById(R.id.rv_NewDocument);

        recyclerViewNewDocument.setLayoutManager(linearLayoutManager);
        documentAdapter_NewDocumet = new DocumentNewAdapter(NewDocumentActivity.this,getListDocument());
        recyclerViewNewDocument.setAdapter(documentAdapter_NewDocumet);

    }

    private List<DocumentNew> getListDocument() {

        List<DocumentNew> list = new ArrayList<>();
        list.add(new DocumentNew("99990","https://thuvienphapluat.vn/van-ban/Thuong-mai/Thong-bao-176-TB-VPCP-2022-ket-luan-tai-buoi-lam-viec-voi-lanh-dao-Son-La-517695.aspx","THÔNG BÁO","KẾT LUẬN CỦA THỦ TƯỚNG CHÍNH PHỦ PHẠM MINH CHÍNH TẠI BUỔI LÀM VIỆC VỚI LÃNH ĐẠO TỈNH SƠN LA","17/06/2022"));
        list.add(new DocumentNew("99991","https://thuvienphapluat.vn/van-ban/The-thao-Y-te/Quyet-dinh-1577-QD-BYT-2022-Ke-hoach-thuc-hien-Quyet-dinh-2215-QD-TTg-517660.aspx","QUYẾT ĐỊNH","PHÊ DUYỆT KẾ HOẠCH TRIỂN KHAI THỰC HIỆN QUYẾT ĐỊNH SỐ 2215/QĐ-TTG NGÀY 28/12/2021 CỦA THỦ TƯỚNG CHÍNH PHỦ VỀ VIỆC BAN HÀNH KẾ HOẠCH HÀNH ĐỘNG QUỐC GIA KHẮC PHỤC HẬU QUẢ CHẤT ĐỘC HÓA HỌC/DIOXIN SAU CHIẾN TRANH Ở VIỆT NAM CỦA BỘ Y TẾ GIAI ĐOẠN 2022-2030","17/06/2022"));
        list.add(new DocumentNew("99992","https://thuvienphapluat.vn/van-ban/The-thao-Y-te/Quyet-dinh-1571-QD-BYT-2022-tai-lieu-huong-dan-theo-doi-do-me-an-than-bang-dien-nao-so-hoa-517631.aspx","QUYẾT ĐỊNH","VỀ VIỆC BAN HÀNH TÀI LIỆU “HƯỚNG DẪN QUY TRÌNH KỸ THUẬT THEO DÕI ĐỘ MÊ, AN THẦN TRONG GÂY MÊ HỒI SỨC VÀ HỒI SỨC TÍCH CỰC BẰNG ĐIỆN NÃO SỐ HÓA (BAO GỒM BIS, ENTROPY VÀ CÁC THIẾT BỊ CÓ CÔNG DỤNG TƯƠNG ĐƯƠNG)”","17/06/2022"));
        list.add(new DocumentNew("99993","https://thuvienphapluat.vn/van-ban/Bo-may-hanh-chinh/Quyet-dinh-1750-QD-UBND-2022-quy-trinh-thu-tuc-hanh-chinh-So-Lao-dong-Can-Tho-517713.aspx","QUYẾT ĐỊNH","PHÊ DUYỆT QUY TRÌNH NỘI BỘ GIẢI QUYẾT THỦ TỤC HÀNH CHÍNH THUỘC THẨM QUYỀN TIẾP NHẬN VÀ GIẢI QUYẾT CỦA SỞ LAO ĐỘNG - THƯƠNG BINH VÀ XÃ HỘI; SỞ Y TẾ; BẢO HIỂM XÃ HỘI THÀNH PHỐ; ỦY BAN NHÂN DÂN CẤP HUYỆN; ỦY BAN NHÂN DÂN CẤP XÃ.","17/06/2022"));
        list.add(new DocumentNew("99994","https://thuvienphapluat.vn/van-ban/Bo-may-hanh-chinh/Quyet-dinh-1310-QD-BTNMT-2022-cong-bo-thu-tuc-hanh-chinh-dia-chat-va-khoang-san-517615.aspx","QUYẾT ĐỊNH","VỀ VIỆC CÔNG BỐ THỦ TỤC HÀNH CHÍNH BỊ BÃI BỎ TRONG LĨNH VỰC ĐỊA CHẤT VÀ KHOÁNG SẢN THUỘC PHẠM VI CHỨC NĂNG QUẢN LÝ CỦA BỘ TÀI NGUYÊN VÀ MÔI TRƯỜNG","17/06/2022"));
        list.add(new DocumentNew("99995","https://thuvienphapluat.vn/van-ban/Cong-nghe-thong-tin/Quyet-dinh-739-QD-TTg-2022-sua-doi-Quyet-dinh-1296-QD-TTg-co-so-du-lieu-tac-pham-van-hoc-517757.aspx","QUYẾT ĐỊNH","SỬA ĐỔI, BỔ SUNG NỘI DUNG ĐIỂM B KHOẢN 4 ĐIỀU 1 QUYẾT ĐỊNH SỐ 1296/QĐ-TTG NGÀY 24 THÁNG 8 NĂM 2020 CỦA THỦ TƯỚNG CHÍNH PHỦ VỀ VIỆC PHÊ DUYỆT CHƯƠNG TRÌNH “NGHIÊN CỨU, XÂY DỰNG CƠ SỞ DỮ LIỆU VÀ CÔNG BỐ TÁC PHẨM VĂN HỌC NGHỆ THUẬT VIỆT NAM TRÊN NỀN TẢNG CÁCH MẠNG CÔNG NGHIỆP LẦN THỨ TƯ”","17/06/2022"));
        list.add(new DocumentNew("99996","https://thuvienphapluat.vn/van-ban/Thuong-mai/Thong-tu-08-2022-TT-BGTVT-sua-doi-Thong-tu-kinh-doanh-linh-vuc-hang-hai-517486.aspx","THÔNG TƯ","SỬA ĐỔI, BỔ SUNG MỘT SỐ ĐIỀU CỦA CÁC THÔNG TƯ QUY ĐỊNH LIÊN QUAN ĐẾN HOẠT ĐỘNG KINH DOANH TRONG LĨNH VỰC HÀNG HẢI","16/06/2022"));
        list.add(new DocumentNew("99997","https://thuvienphapluat.vn/van-ban/Thuong-mai/Quyet-dinh-721-QD-TTg-2022-phuong-an-cat-giam-quy-dinh-kinh-doanh-thuoc-Bo-Tai-nguyen-517440.aspx","QUYẾT ĐỊNH","PHÊ DUYỆT PHƯƠNG ÁN CẮT GIẢM, ĐƠN GIẢN HÓA QUY ĐỊNH LIÊN QUAN ĐẾN HOẠT ĐỘNG KINH DOANH THUỘC PHẠM VI CHỨC NĂNG QUẢN LÝ NHÀ NƯỚC CỦA BỘ TÀI NGUYÊN VÀ MÔI TRƯỜNG","16/06/2022"));
        list.add(new DocumentNew("99998","https://thuvienphapluat.vn/van-ban/Thuong-mai/Chi-thi-11-CT-UBND-2022-tang-cuong-cong-tac-dam-bao-an-toan-dien-Vinh-Long-517612.aspx","CHỈ THỊ","VỀ VIỆC TĂNG CƯỜNG CÔNG TÁC ĐẢM BẢO AN TOÀN ĐIỆN, BẢO VỆ HÀNH LANG AN TOÀN LƯỚI ĐIỆN CAO ÁP TRÊN ĐỊA BÀN TỈNH VĨNH LONG","16/06/2022"));
        list.add(new DocumentNew("99999","https://thuvienphapluat.vn/van-ban/Tai-nguyen-Moi-truong/Thong-tu-03-2022-TT-BNNPTNT-sua-doi-Thong-tu-05-2018-TT-BNNPTNT-517672.aspx","THÔNG TƯ","SỬA ĐỔI, BỔ SUNG MỘT SỐ ĐIỀU CỦA THÔNG TƯ SỐ 05/2018/TT-BNNPTNT NGÀY 15 THÁNG 5 NĂM 2018 CỦA BỘ TRƯỞNG BỘ NÔNG NGHIỆP VÀ PHÁT TRIỂN NÔNG THÔN QUY ĐỊNH CHI TIẾT MỘT SỐ ĐIỀU CỦA LUẬT THỦY LỢI","16/06/2022"));
        return list;
    }
}