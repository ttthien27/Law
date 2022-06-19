package com.android.Law.activity;

import androidx.annotation.NonNull;
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
import com.android.Law.firebase.LawDatabaseContract;
import com.android.Law.firebase.UserAccountsRequester;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneToForgotPassActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private UserAccountsRequester userAccountsRequester;
    private String TAG = "firebase - FORGOT";
    private String mVerificationId;
    private Boolean checkPhone;

    EditText edtNum1, edtNum2, edtNum3, edtNum4, edtNum5, edtNum6;
    EditText edtPhone;
    Button btnSendOTP;
    Button btnVerify;


    String phone_intent;


    private String mPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_to_forgot_pass);
        mAuth = FirebaseAuth.getInstance();

        edtNum1 = findViewById(R.id.edtNum1_forgotPass);
        edtNum2 = findViewById(R.id.edtNum2_forgotPass);
        edtNum3 = findViewById(R.id.edtNum3_forgotPass);
        edtNum4 = findViewById(R.id.edtNum4_forgotPass);
        edtNum5 = findViewById(R.id.edtNum5_forgotPass);
        edtNum6 = findViewById(R.id.edtNum6_forgotPass);
        edtPhone = findViewById(R.id.edt_Forgot_Phone);
        btnSendOTP = findViewById(R.id.btnSendOTP_forgotPass);
        btnVerify = findViewById(R.id.btnVerify_forgotPass);

        userAccountsRequester = new UserAccountsRequester(this);

        Intent intent = getIntent();
        //name_intent = intent.getStringExtra(LawDatabaseContract.UserEntry.FULL_NAME_ARM);
        //phone_intent = intent.getStringExtra(LawDatabaseContract.UserEntry.PHONE_ARM);
        //pass_intent = intent.getStringExtra(LawDatabaseContract.UserEntry.PASSWORD_ARM);

        //String cv_phone = phone_intent.replace("0", "+84");

        //tvPhone.setText(phone_intent);
        //Toast.makeText(getApplicationContext(), name_intent + phone_intent + pass_intent, Toast.LENGTH_SHORT).show();

        edtPhone.addTextChangedListener(textWatcherPhone);


        edtNum1.addTextChangedListener(textWatcher1);
        edtNum2.addTextChangedListener(textWatcher2);
        edtNum3.addTextChangedListener(textWatcher3);
        edtNum4.addTextChangedListener(textWatcher4);
        edtNum5.addTextChangedListener(textWatcher5);

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPhone){
                    Toast.makeText(getApplicationContext(), "Vui lòng đợi", Toast.LENGTH_SHORT).show();
                    sendVerificationCode(mPhone);
                }
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = edtNum1.getText().toString() +
                        edtNum2.getText().toString() +
                        edtNum3.getText().toString() +
                        edtNum4.getText().toString() +
                        edtNum5.getText().toString() +
                        edtNum6.getText().toString();
                if(otp.length()==6)
                    verifyCode(otp);
            }
        });
    }

    private void sendVerificationCode(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // số điện thoại cần xác thực
                        .setTimeout(60L, TimeUnit.SECONDS) //thời gian timeout
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback xác thực sđt
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            //Hàm này được gọi trong hai trường hợp:
            //1. Trong một số trường hợp, điện thoại di động được xác minh tự động mà không cần mã xác minh.
            //2. Trên một số thiết bị, các dịch vụ của Google Play phát hiện SMS đến và thực hiện quy trình xác minh mà không cần người dùng thực hiện bất kỳ hành động nào.
//            Log.d(TAG, "onVerificationCompleted:" + credential);
//            Toast.makeText(VerifyPhoneNumber.this, credential.getSmsCode(),Toast.LENGTH_SHORT).show();
//            System.out.println(credential.getSmsCode());
            //tự động điền mã OTP
//            edtNum1.setText(credential.getSmsCode().substring(0,1));
//            edtNum2.setText(credential.getSmsCode().substring(1,2));
//            edtNum3.setText(credential.getSmsCode().substring(2,3));
//            edtNum4.setText(credential.getSmsCode().substring(3,4));
//            edtNum5.setText(credential.getSmsCode().substring(4,5));
//            edtNum6.setText(credential.getSmsCode().substring(5,6));

//            verifyCode(credential.getSmsCode());
        }

        //fail
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.w(TAG, "onVerificationFailed", e);
            Toast.makeText(VerifyPhoneToForgotPassActivity.this, "Thất bại",Toast.LENGTH_SHORT).show();
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(VerifyPhoneToForgotPassActivity.this, "yêu cầu thất bại",Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(VerifyPhoneToForgotPassActivity.this, "Quota không đủ",Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            Log.d(TAG, "onCodeSent:" + verificationId);
            Toast.makeText(getApplicationContext(), "Đã gửi OTP", Toast.LENGTH_SHORT).show();
            mVerificationId = verificationId;
            mResendToken = token;
        }
    };

    //code xác thực OTP
    private void verifyCode(String code) {
//        Toast.makeText(MainActivity.this, "Đang xác thực",Toast.LENGTH_SHORT).show();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        changePassWithPhoneAuthCredential(credential);
    }

    private void changePassWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            /*FirebaseUser user = task.getResult().getUser();
                            //String encrypts = MD5Encode.endCode(pass_intent);
                            UserAccount userAccounts = new UserAccount(name_intent, phone_intent,"",pass_intent,false);
                            String accKey = userAccountsRequester.createAnUserAccount(userAccounts);
                            if (accKey != null){
                                Toast.makeText(VerifyPhoneToChangePassActivity.this,"Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                sendUserToLogin();
                            }
                            else{
                                Toast.makeText(VerifyPhoneToChangePassActivity.this,"Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(VerifyPhoneToChangePassActivity.this, "Thành công",Toast.LENGTH_SHORT).show();*/
                            sendUserToChangePass();

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyPhoneToForgotPassActivity.this, "Lỗi",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }

    private void sendUserToChangePass() {
        Intent intent = new Intent(VerifyPhoneToForgotPassActivity.this, ChangePasswordActivity.class);
        intent.putExtra(LawDatabaseContract.UserEntry.PHONE_ARM,"0"+mPhone.substring(3,mPhone.length()));
        intent.putExtra("navigationActivity",2);
        //intent.putExtra(LawDatabaseContract.UserEntry.PASSWORD_ARM,pass_intent);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private TextWatcher textWatcher1 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtNum1.getText().toString().length() >= 1) {
                edtNum2.requestFocus();
                edtNum2.setSelection(edtNum2.getText().length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private TextWatcher textWatcher2 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtNum2.getText().toString().length() >= 1) {
                edtNum3.requestFocus();
                edtNum3.setSelection(edtNum3.getText().length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private TextWatcher textWatcher3 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtNum3.getText().toString().length() >= 1) {
                edtNum4.requestFocus();
                edtNum4.setSelection(edtNum4.getText().length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private TextWatcher textWatcher4 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtNum4.getText().toString().length() >= 1) {
                edtNum5.requestFocus();
                edtNum5.setSelection(edtNum5.getText().length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private TextWatcher textWatcher5 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtNum5.getText().toString().length() >= 1) {
                edtNum6.requestFocus();
                edtNum6.setSelection(edtNum6.getText().length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private TextWatcher textWatcherPhone = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (edtPhone.getText().length() == 0) {
                edtPhone.setError("Số điện thoại không được để trống");
            } else if (!edtPhone.getText().toString().matches("[0-9]+") || edtPhone.getText().length() != 10) {
                edtPhone.setError("Số điện thoại không hợp lệ");
            } else {
                edtPhone.setError(null);
                checkPhone = true;
                mPhone ="+84" + edtPhone.getText().toString().substring(1,edtPhone.getText().toString().length());
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