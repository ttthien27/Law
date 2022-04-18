package com.android.Law.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import com.android.Law.R;
import com.android.Law.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tabItem1, tabItem2, tabItem3;

    // selected tab number. we have 3 tabs so value must be lie between 1 - 3
    // we are setting default value 1. because by default first tab will be selected
    private int selectedTabNumber = 1;

    public LibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryFragment newInstance(String param1, String param2) {
        LibraryFragment fragment = new LibraryFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_library, container, false);
        View view = inflater.inflate(R.layout.fragment_library, container, false);


        /*viewPager = view.findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);*/
        tabItem1 = view.findViewById(R.id.tabItem1);
        tabItem2 = view.findViewById(R.id.tabItem2);
        tabItem3 = view.findViewById(R.id.tabItem3);

        // selecting first fragment by default
        ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, FollowFragment.class, null)
                .commit();

        tabItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectTab(1);
            }
        });

        tabItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectTab(2);
            }
        });

        tabItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectTab(3);
            }
        });
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);


        return view;
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

    private void selectTab(int tabNumber) {

        TextView selectedTextView;

        TextView nonSelectedTextView1;
        TextView nonSelectedTextView2;
        // if you have more than three tabs then create nonSelectedTextView3.......n

        if (tabNumber == 1) {

            // user has selected first tab so 1st TextView is selected
            selectedTextView = tabItem1;

            // other two textviews are non selected
            nonSelectedTextView1 = tabItem2;
            nonSelectedTextView2 = tabItem3;

            // setting fragment to the fragment container view
            ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, FollowFragment.class, null)
                    .commit();

        } else if (tabNumber == 2) {

            // user has selected second tab so 2nd TextView is selected
            selectedTextView = tabItem2;

            // other two textviews are non selected. 1st and 3rd Tab is non selected
            nonSelectedTextView1 = tabItem1;
            nonSelectedTextView2 = tabItem3;

            // setting fragment to the fragment container view
            ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, DowloadFragment.class, null)
                    .commit();
        } else {

            // user has selected third tab so 3rd TextView is selected
            selectedTextView = tabItem3;

            // other two textviews are non selected. 1st and 2nd Tab is non selected
            nonSelectedTextView1 = tabItem1;
            nonSelectedTextView2 = tabItem2;

            // setting fragment to the fragment container view
            ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, SeenFragment.class, null)
                    .commit();
        }


        float slideTo = (tabNumber - selectedTabNumber) * selectedTextView.getWidth();

        // creating translate animation
        TranslateAnimation translateAnimation = new TranslateAnimation(0, slideTo, 0, 0);
        translateAnimation.setDuration(100);

        // checking for previously selected tab
        if (selectedTabNumber == 1) {
            tabItem1.startAnimation(translateAnimation);
        } else if (selectedTabNumber == 2) {
            tabItem2.startAnimation(translateAnimation);
        } else {
            tabItem3.startAnimation(translateAnimation);
        }

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // change design of selected tab's TextView
                selectedTextView.setBackgroundResource(R.drawable.round_back_white_100);
                selectedTextView.setTypeface(null, Typeface.BOLD);
                selectedTextView.setTextColor(Color.parseColor("#d703082b"));

                // change design of non selected tab's TextViews
                nonSelectedTextView1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView1.setTextColor(Color.parseColor("#d703082b"));
                nonSelectedTextView1.setTypeface(null, Typeface.NORMAL);

                nonSelectedTextView2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                nonSelectedTextView2.setTextColor(Color.parseColor("#d703082b"));
                nonSelectedTextView2.setTypeface(null, Typeface.NORMAL);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        selectedTabNumber = tabNumber;
    }
}