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
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {

    private TextView tvRegister;
    private Button btnLogin;
    private TextView tvForgetPass;
    private EditText edtPhone;
    private EditText edtPass;
    private UserAccountsRequester userAccountsRequester;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvRegister = findViewById(R.id.textView_Login_SignUp);
        btnLogin = findViewById(R.id.btn_Login_Login);
        tvForgetPass = findViewById(R.id.tv_Login_ForgetPass);
        edtPhone = findViewById(R.id.edt_Login_Phone);
        edtPass = findViewById(R.id.edt_Login_Pass);

        userAccountsRequester = new UserAccountsRequester(this);
        sessionManager = new SessionManager(this);

        Intent intent = getIntent();
        if (intent != null) {
            edtPhone.setText(intent.getStringExtra(LawDatabaseContract.UserEntry.PHONE_ARM));
            edtPass.setText(intent.getStringExtra(LawDatabaseContract.UserEntry.PASSWORD_ARM));
        }

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(intent);
                onClickLogin();
            }
        });

        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, VerifyPhoneToForgotPassActivity.class);
                startActivity(intent);
            }
        });

        edtPhone.addTextChangedListener(textWatcherPhone);
        edtPass.addTextChangedListener(textWatcherPass);


    }

    private TextWatcher textWatcherPhone = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtPhone.getText().length() == 0) {
                edtPhone.setError("Số điện thoại không được để trống");
            } else if (!edtPhone.getText().toString().matches("[0-9]+") || edtPhone.getText().length() != 10) {
                edtPhone.setError("Số điện thoại không hợp lệ");
            } else {
                edtPhone.setError(null);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher textWatcherPass = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtPass.getText().length() == 0) {
                edtPass.setError("Mật khẩu không được để trống");
            } else {
                edtPass.setError(null);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void onClickLogin() {
        String userPhone = edtPhone.getText().toString();
        String userPass = edtPass.getText().toString();
        //String encrypts = MD5Encode.endCode(pass);
//        userAccountsRequester.isLogin(phone);

        DatabaseReference databaseReference = userAccountsRequester.getmDatabase();
        Query query = databaseReference.orderByChild(LawDatabaseContract.UserEntry.PHONE_ARM).equalTo(userPhone.trim());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        String fullName = user.child("fullName").getValue(String.class);
                        String email = user.child("email").getValue(String.class);
                        String password = user.child("password").getValue(String.class);
                        String phone = user.child("phone").getValue(String.class);
                        Boolean login = user.child("login").getValue(Boolean.class);
                        UserAccount userAccounts = new UserAccount(fullName,phone,email,password,login);
                        if (userAccounts.getLogin() == true) {
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
                        }
                    }
                } else {
                    edtPhone.setError("Tài khoản không tồn tại");
                    edtPhone.requestFocus();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void sendUserToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}