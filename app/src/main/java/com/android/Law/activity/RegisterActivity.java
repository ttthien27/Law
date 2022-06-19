package com.android.Law.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.Law.R;
import com.android.Law.firebase.LawDatabaseContract;
import com.android.Law.firebase.UserAccountsRequester;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtPhone;
    private EditText edtPass;
    private EditText edtRePass;
    private EditText edtFullName;
    private Button btnRegister;
    private UserAccountsRequester userAccountsRequester;
    private Boolean a = false,b = false, c = false, d = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtPhone = findViewById(R.id.edt_Register_Phone);
        edtPass = findViewById(R.id.edt_Register_Pass);
        edtRePass = findViewById(R.id.edt_Register_RePass);
        edtFullName = findViewById(R.id.edt_Register_FullName);
        btnRegister = findViewById(R.id.btn_register_register);

        edtPhone.addTextChangedListener(textWatcherPhone);
        edtFullName.addTextChangedListener(textWatcherName);
        edtPass.addTextChangedListener(textWatcherPass);
        edtRePass.addTextChangedListener(textWatcherRePass);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a && b && c && d)
                onClickRegister();
            }
        });


    }

    private TextWatcher textWatcherPhone = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (edtPhone.getText().toString().length() == 0) {
                edtPhone.setError("Số điện thoại không được để trống");
                edtPhone.setEnabled(true);
                a = false;
            } else if (!edtPhone.getText().toString().matches("[0-9]+") || edtPhone.getText().length() != 10) {
                edtPhone.setError("Số điện thoại không hợp lệ");
                a = false;
            }else {
                edtPhone.setError(null);
                a = true;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private TextWatcher textWatcherName = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (edtFullName.getText().toString().length() == 0) {
                edtFullName.setError("Họ tên không được để trống");
                edtFullName.setEnabled(true);
                b = false;
            } else {
                edtFullName.setError(null);
                b = true;
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

    private void onClickRegister() {
        String phone = edtPhone.getText().toString();
        String name = edtFullName.getText().toString();
        String pass = edtPass.getText().toString();
        String re_pass = edtRePass.getText().toString();

        sendUserToVerifyPhone(name, phone, pass);
    }

    private void sendUserToVerifyPhone(String name, String phone, String pass) {
        Intent intent = new Intent(RegisterActivity.this, VerifyPhoneActivity.class);
        intent.putExtra(LawDatabaseContract.UserEntry.FULL_NAME_ARM, name);
        intent.putExtra(LawDatabaseContract.UserEntry.PHONE_ARM, phone);
        intent.putExtra(LawDatabaseContract.UserEntry.PASSWORD_ARM, pass);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}