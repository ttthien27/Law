package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.android.Law.R;
import com.android.Law.fragment.AboutFragment;
import com.android.Law.fragment.AccountFragment;
import com.android.Law.fragment.HomeFragment;
import com.android.Law.fragment.LibraryFragment;
import com.android.Law.fragment.NewsFragment;
import com.android.Law.fragment.SearchFragment;
import com.android.Law.fragment.SuportFragment;
import com.android.Law.models.Query;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.tabs.TabLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_News;
    private MeowBottomNavigation bottomNavigation;
    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*bottomNavigation=findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_bookmarks_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_library_books_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_support_agent_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_baseline_account_circle_24));

        bottomNavigation.show(3,true);

        replace(new HomeFragment());
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new LibraryFragment());
                        break;

                    case 2:
                        replace(new NewsFragment());
                        break;

                    case 3:
                        replace(new HomeFragment());
                        break;

                    case 4:
                        replace(new SuportFragment());
                        break;

                    case 5:
                        replace(new AccountFragment());
                        break;

                    default:
                        break;

                }
                return null;
            }
        });*/

        int index= -1; int indexSupportFragment = 1; int indexSearchFragment = 2; int indexAccountFragment = 3;
        Intent intent=getIntent();
        index = intent.getIntExtra("index",index);

        chipNavigationBar = findViewById(R.id.chipNavigation);

        if(index==-1){
            chipNavigationBar.setItemSelected(R.id.item_Home, true);

            getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    switch (i) {
                        case R.id.item_Home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.item_Library:
                            fragment = new LibraryFragment();
                            break;
                        case R.id.item_News:
                            fragment = new SearchFragment();
                            break;
                        case R.id.item_Support:
                            fragment = new SuportFragment();
                            break;
                        case R.id.item_Account:
                            fragment = new AccountFragment();
                            break;
                    }

                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    }
                }
            });
        }else if(index == indexSupportFragment){
            chipNavigationBar.setItemSelected(R.id.item_Support, true);

            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SuportFragment()).commit();

            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    switch (i) {
                        case R.id.item_Home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.item_Library:
                            fragment = new LibraryFragment();
                            break;
                        case R.id.item_News:
                            fragment = new SearchFragment();
                            break;
                        case R.id.item_Support:
                            fragment = new SuportFragment();
                            break;
                        case R.id.item_Account:
                            fragment = new AccountFragment();
                            break;
                    }

                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    }
                }
            });
        }else if(index == indexSearchFragment){
            chipNavigationBar.setItemSelected(R.id.item_News, true);
            Query query = new Query(intent.getStringExtra("query"));
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment(query)).commit();

            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    switch (i) {
                        case R.id.item_Home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.item_Library:
                            fragment = new LibraryFragment();
                            break;
                        case R.id.item_News:
                            fragment = new SearchFragment();
                            break;
                        case R.id.item_Support:
                            fragment = new SuportFragment();
                            break;
                        case R.id.item_Account:
                            fragment = new AccountFragment();
                            break;
                    }

                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    }
                }
            });
        }else if(index == indexAccountFragment){
            chipNavigationBar.setItemSelected(R.id.item_Account, true);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new AccountFragment()).commit();

            chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
                @Override
                public void onItemSelected(int i) {
                    switch (i) {
                        case R.id.item_Home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.item_Library:
                            fragment = new LibraryFragment();
                            break;
                        case R.id.item_News:
                            fragment = new SearchFragment();
                            break;
                        case R.id.item_Support:
                            fragment = new SuportFragment();
                            break;
                        case R.id.item_Account:
                            fragment = new AccountFragment();
                            break;
                    }

                    if (fragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                    }
                }
            });
        }



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    /*private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }*/


}