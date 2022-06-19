package com.android.Law;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.Law.contract.LawContract.UserEntry;
import com.android.Law.models.UserAccount;

public class SessionManager {

    public static final String TAG = SessionManager.class.getName();
    private final SharedPreferences userSession;
    private final SharedPreferences.Editor userDataEditor;
    private final Context mContext;

    public SessionManager(Context mContext) {
        this.mContext = mContext;
        userSession = mContext.getSharedPreferences("userSession", Context.MODE_PRIVATE);
        userDataEditor = userSession.edit();
    }

    public void initUserSession(String phone, String fullName) {
        userDataEditor.putString(UserEntry.PHONE_ARM, phone);
        userDataEditor.putString(UserEntry.FULL_NAME_ARM, fullName);
        userDataEditor.commit();
    }

    //test init User
    public void initUserSession(UserAccount userAccount) {
        userAccount.setLogin(true);
        userDataEditor.putString(UserEntry.PHONE_ARM, userAccount.phone);
        userDataEditor.putString(UserEntry.FULL_NAME_ARM, userAccount.fullName);
        userDataEditor.putString(UserEntry.IS_LOGIN_ARM, userAccount.getLogin().toString());
        userDataEditor.commit();
    }

    public boolean isLogin() {
        String login = userSession.getString(UserEntry.IS_LOGIN_ARM, null);
        boolean log = Boolean.parseBoolean(login);
        return log;
    }

    public Bundle getUserData() {
        String phone = userSession.getString(UserEntry.PHONE_ARM, null);
        String fullName = userSession.getString(UserEntry.FULL_NAME_ARM, null);
        String email = userSession.getString(UserEntry.EMAIL_ARM,null);
        Bundle bundle = new Bundle();
        bundle.putString(UserEntry.PHONE_ARM, phone);
        bundle.putString(UserEntry.FULL_NAME_ARM, fullName);
        bundle.putString(UserEntry.EMAIL_ARM, email);
        return bundle;
    }

    public String getUserEmail(){
        return userSession.getString(UserEntry.EMAIL_ARM,null);
    }

    public void clearUserData() {
        userDataEditor.clear();
        userDataEditor.commit();
    }
}
