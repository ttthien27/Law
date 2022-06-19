package com.android.Law.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.SessionManager;
import com.android.Law.firebase.LawDatabaseContract;
import com.android.Law.firebase.UserAccountsRequester;
import com.android.Law.models.UserAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ChangePasswordActivity extends AppCompatActivity {

    private UserAccountsRequester userAccountsRequester;
    private SessionManager sessionManager;
    private EditText edtPass;
    private EditText edtRePass;
    private Button btnChangePass;
    private Boolean c = false, d = false;
    private String phone_intent;
    private int navigActivity = -1;// == 1 => AccountFragment // ==2 => LoginActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        edtPass = findViewById(R.id.edt_ChangePass_Pass);
        edtRePass = findViewById(R.id.edt_ChangePass_RePass);
        btnChangePass = findViewById(R.id.btn_ChangePass_ChangePass);

        userAccountsRequester = new UserAccountsRequester(this);
        sessionManager = new SessionManager(this);

        edtPass.addTextChangedListener(textWatcherPass);
        edtRePass.addTextChangedListener(textWatcherRePass);

        Intent intent = getIntent();
        phone_intent = intent.getStringExtra(LawDatabaseContract.UserEntry.PHONE_ARM);
        navigActivity = intent.getIntExtra("navigationActivity",navigActivity);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c && d){
                    onClickChangePass();
                }
            }
        });

    }

    private void onClickChangePass() {
        //String encrypts = MD5Encode.endCode(pass);
//        userAccountsRequester.isLogin(phone);

        DatabaseReference databaseReference = userAccountsRequester.getmDatabase();
        Query query = databaseReference.orderByChild(LawDatabaseContract.UserEntry.PHONE_ARM).equalTo(phone_intent.trim());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        /*String fullName = user.child("fullName").getValue(String.class);
                        String email = user.child("email").getValue(String.class);
                        String password = user.child("password").getValue(String.class);
                        String phone = user.child("phone").getValue(String.class);
                        Boolean login = user.child("login").getValue(Boolean.class);
                        UserAccount userAccounts = new UserAccount(fullName,phone,email,password,login);*/

                        String newPass = edtPass.getText().toString();
                        userAccountsRequester.updateUserAccount(phone_intent,newPass);
                        Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        if(navigActivity==1){
                            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                            intent.putExtra("index",3);
                            startActivity(intent);
                        }else if(navigActivity==2){
                            Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                            //intent.putExtra("index",3);
                            startActivity(intent);
                        }


                        /*if (userAccounts.getLogin() == true) {
                            Toast.makeText(LoginActivity.this, "Tài khoản đã được đăng nhập trên một thiết bị khác", Toast.LENGTH_SHORT).show();
                        } else {
                            if (userAccounts.password.equals(userPass)) {
                                userAccountsRequester.isLogin(userPhone, true);
                                Log.d("check login", "onDataChange: --------------");
                                Toast.makeText(LoginActivity.this, " Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                sessionManager.initUserSession(userAccounts);
                                sendUserToHome();
                            } else {
                                edtPass.setError("Sai mật khẩu");
                                edtPass.requestFocus();
                            }
                        }*/
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private TextWatcher textWatcherPass = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean check_1 = false, check_2  = false;
            if (edtPass.getText().toString().matches("")) {
                edtPass.setError("Mật khẩu không được để trống");
                check_1= false;
            } else {
                edtPass.setError(null);
                check_1 = true;
            }
            if (edtPass.getText().length() < 6) {
                edtPass.setError("Mật khẩu tối thiểu phải 6 kí tự");
                check_2 = false;
            } else {
                edtPass.setError(null);
                check_2 = true;
            }
            if(check_1 == true && check_2 == true){
                c = true;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private TextWatcher textWatcherRePass = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (!edtRePass.getText().toString().equals(edtPass.getText().toString())) {
                edtRePass.setError("Mật khẩu không trùng khớp");
                d = false;
            } else {
                edtRePass.setError(null);
                d = true;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}