package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.android.Law.R;
import com.android.Law.fragment.CategoryFragment;
import com.android.Law.models.Query;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Query query1 = new Query("Giao Thông vận tải");String str1 = "Xử phạt@An toàn giao thông@Tai nạn@Vi phạm@Xe ưu tiên@Giấy phép lái xe";
        adapter.addFrag(new CategoryFragment(query1,str1), "Giao Thông - Vận tải");
        Query query2 = new Query("Giáo dục");String str2 = "Phổ thông@Dạy nghề@Giáo dục thường xuyên@Mầm non@Tuyển sinh@Đào tạo";
        adapter.addFrag(new CategoryFragment(query2,str2), "Giáo dục");
        Query query3 = new Query("Đầu tư");String str3 = "Nước ngoài@Doanh nghiệp@Quỹ đầu tư@Thủ tục@Hỗ trợ vốn@Đầu tư phát triển";
        adapter.addFrag(new CategoryFragment(query3,str3), "Đầu tư");
        Query query4 = new Query("Bảo hiểm");String str4 = "Y tế@Thất nghiệp@Tai nạn lao động@Xã hội@Bảo hiểm xe@Lương hưu";
        adapter.addFrag(new CategoryFragment(query4,str4), "Bảo hiểm");
        Query query5 = new Query("Tài nguyên môi trường");String str5 = "Biển và hải đảo@Khai thác@Khoáng sản@Tài nguyên nước@Bảo vệ@Quản lý";
        adapter.addFrag(new CategoryFragment(query5,str5), "Tài nguyên - Môi trường");
        Query query6 = new Query("Lao động tiền lương");String str6 = "Lương hưu@Mức lương@Tai nạn lao động@Thương binh@Việc làm@Phụ cấp";
        adapter.addFrag(new CategoryFragment(query6,str6), "Lao động - Tiền lương");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}