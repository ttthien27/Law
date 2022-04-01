package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.android.Law.R;
import com.android.Law.fragment.AboutFragment;
import com.android.Law.fragment.AccountFragment;
import com.android.Law.fragment.HomeFragment;
import com.android.Law.fragment.LibraryFragment;
import com.android.Law.fragment.NewsFragment;
import com.android.Law.fragment.SuportFragment;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.tabs.TabLayout;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_News;
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation=findViewById(R.id.bottom_navigation);

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
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }


}