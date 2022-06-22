package com.android.Law.firebase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.Law.models.DocumentMostView;
import com.android.Law.models.UserAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserAccountsRequester {
    private DatabaseReference mDatabase;
    private Context mContext;
    private List<Boolean> stateLogin;

    public DatabaseReference getmDatabase(){
        return mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME);
    }

    public UserAccountsRequester(Context context){
        mDatabase = FirebaseDatabase
                .getInstance("https://appphapluat-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();
        this.mContext = context;
        stateLogin = new ArrayList<>();
    }

    //Tạo một tài khoản
    public String createAnUserAccount(UserAccount userAccount) {
        String accKey =  mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME).push().getKey();
        if (accKey != null) {
            mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME).child(accKey).setValue(userAccount);
            return accKey;
        }
        return null;
    }

    //Cập nhật
    public void updateUserAccount(String phone, String pass){
        //String encrypt = MD5Encode.endCode(pass);
        Query query = mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME)
                .orderByChild(LawDatabaseContract.UserEntry.PHONE_ARM)
                .equalTo(phone.trim());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        String key = user.getKey();
                        mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.UserEntry.PASSWORD_ARM)
                                .setValue(pass);
                        System.out.println("cập nhật thành công");
                    }
                } else {
                    System.out.println("cập nhật thất bại");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //change State_Login on Firebase
    public void isLogin( String phone,boolean state){
        Query query = mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME)
                .orderByChild(LawDatabaseContract.UserEntry.PHONE_ARM)
                .equalTo(phone.trim());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = dataSnapshot.getKey();
                        mDatabase.child(LawDatabaseContract.UserEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.UserEntry.IS_LOGIN_ARM)
                                .setValue(state);
                        //
//                        readData(new StateLoginFirebaseCallback() {
//                            @Override
//                            public void onCallBack(List<Boolean> list) {
//                                System.out.println("TEST: "+list.toString());
//                            }
//                        },phone);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
