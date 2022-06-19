package com.android.Law.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.SessionManager;
import com.android.Law.activity.ChangePasswordActivity;
import com.android.Law.activity.LoginActivity;
import com.android.Law.activity.MainActivity;
import com.android.Law.activity.VerifyPhoneToChangePassActivity;
import com.android.Law.firebase.LawDatabaseContract;
import com.android.Law.firebase.UserAccountsRequester;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private Activity mActivity;
    private SessionManager sessionManager;
    private UserAccountsRequester userAccountsRequester;
    private Button btn_Evaluate;
    private Button btn_ChangePass;
    private Button btn_Logout;

    private Dialog dialog;


    public AccountFragment() {
        // Required empty public constructor
    }

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
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);


        sessionManager = new SessionManager(mContext);
        userAccountsRequester = new UserAccountsRequester(mContext);
        //if(sessionManager.isLogin()){
            btn_Evaluate = view.findViewById(R.id.btn_Account_Evaluate);
            btn_ChangePass = view.findViewById(R.id.btn_Account_ChangePass);
            btn_Logout = view.findViewById(R.id.btn_Account_Logout);

            btn_ChangePass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VerifyPhoneToChangePassActivity.class);
                    intent.putExtra(LawDatabaseContract.UserEntry.PHONE_ARM, sessionManager.getUserData().getString(LawDatabaseContract.UserEntry.PHONE_ARM));
                    startActivity(intent);
                }
            });

            btn_Evaluate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnEvaluate();
                }
            });

            btn_Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userAccountsRequester.isLogin(sessionManager.getUserData().getString(LawDatabaseContract.UserEntry.PHONE_ARM),false);
                    sessionManager.clearUserData();
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }
            });
        //}else {showDialogCheckLogin();
        //}
        return  view;
    }

    private void showDialogCheckLogin(){
        dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.custom_dialog_layout_checklogin);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button Okay = dialog.findViewById(R.id.btn_DialogCheckLogin_Login);
        Button Cancel = dialog.findViewById(R.id.btn_DialogCheckLogin_Cancel);


        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mContext, "Okay", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }

    private void btnEvaluate(){
        dialog = new Dialog(mContext);
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

                //Toast.makeText(mContext, "Okay", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        sessionManager = new SessionManager(mContext);
        Bundle userData = sessionManager.getUserData();
        if(sessionManager.isLogin()){

        }else {
            //showDialogCheckLogin();
        }

    }
}