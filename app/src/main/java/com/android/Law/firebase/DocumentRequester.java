package com.android.Law.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.Law.models.Document;
import com.android.Law.models.DocumentMostView;
import com.android.Law.models.UserAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DocumentRequester {
    private DatabaseReference mDatabase;
    private Context mContext;
    private List<Boolean> stateLogin;

    public DatabaseReference getmDatabase(){
        return mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME);
    }

    public DocumentRequester(){
        mDatabase = FirebaseDatabase
                .getInstance("https://appphapluat-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference();
        //this.mContext = context;
        stateLogin = new ArrayList<>();
    }

    public DatabaseReference find() {
        String accKey =  mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP).push().getKey();
        return mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP).child(accKey);
        //return mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME).child(roomKey);
    }

    //Tạo một document
    public String createDocumentAccount(DocumentMostView documentMostView) {
        String accKey =  mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME).push().getKey();
        if (accKey != null) {
            mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME).child(accKey).setValue(documentMostView);
            return accKey;
        }
        return null;
    }

    //Cập nhật
    public void updateUserAccount(DocumentMostView documentMostView){
        //String encrypt = MD5Encode.endCode(pass);
        //listDocumentViewTop = sortWithViewHighToLow(listDocumentViewTop);
        Query query = mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                .orderByChild(LawDatabaseContract.DocumentEntry.ID_ARM)
                .equalTo(documentMostView.getDocId().trim());
        //List<DocumentMostView> finalListDocumentViewTop = listDocumentViewTop;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot doc : dataSnapshot.getChildren()) {
                        String key = doc.getKey();
                        int view = doc.child(LawDatabaseContract.DocumentEntry.VIEW_ARM).getValue(Integer.class);
                        //view = view +1;documentMostView.setView(view);
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.DocumentEntry.VIEW_ARM)
                                .setValue(view+1);

                        System.out.println("cập nhật");
                    }
                    /*DocumentMostView docInTop = getDocumentViewSoMuch(finalListDocumentViewTop,documentMostView.getView());
                    if(docInTop!=null){
                        changeDocumentMostViewTop(documentMostView,docInTop);
                    }*/
                } else {
                    documentMostView.setView(1);
                    createDocumentAccount(documentMostView);
                    System.out.println("tạo mới");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void changeDocumentMostViewTop(DocumentMostView documentSeen,DocumentMostView docInTop){
        Query query = mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP)
                .orderByChild(LawDatabaseContract.DocumentEntry.ID_ARM)
                .equalTo(docInTop.getDocId().trim());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot doc : dataSnapshot.getChildren()) {
                        String key = doc.getKey();
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP)
                                .child(key).child(LawDatabaseContract.DocumentEntry.ID_ARM)
                                .setValue(documentSeen.getDocId());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP)
                                .child(key).child(LawDatabaseContract.DocumentEntry.URL_ARM)
                                .setValue(documentSeen.getDocUrl());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP)
                                .child(key).child(LawDatabaseContract.DocumentEntry.TITLE_ARM)
                                .setValue(documentSeen.getDocTitle());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP)
                                .child(key).child(LawDatabaseContract.DocumentEntry.DESCRIPTION_ARM)
                                .setValue(documentSeen.getDocDescription());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP)
                                .child(key).child(LawDatabaseContract.DocumentEntry.VIEW_ARM)
                                .setValue(documentSeen.getView());
                        System.out.println("cập nhật");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query2 = mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                .orderByChild(LawDatabaseContract.DocumentEntry.ID_ARM)
                .equalTo(documentSeen.getDocId().trim());
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot doc : dataSnapshot.getChildren()) {
                        String key = doc.getKey();
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.DocumentEntry.ID_ARM)
                                .setValue(docInTop.getDocId());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.DocumentEntry.URL_ARM)
                                .setValue(docInTop.getDocUrl());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.DocumentEntry.TITLE_ARM)
                                .setValue(docInTop.getDocTitle());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.DocumentEntry.DESCRIPTION_ARM)
                                .setValue(docInTop.getDocDescription());
                        mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME)
                                .child(key).child(LawDatabaseContract.DocumentEntry.VIEW_ARM)
                                .setValue(docInTop.getView()-2);
                        System.out.println("cập nhật");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public DocumentMostView getDocumentViewSoMuch(List<DocumentMostView> list, int view){
        list = sortWithViewLowToHigh(list);
        for (DocumentMostView doc: list) {
            if (doc.getView()<view){
                return doc;
            }
        }
        return null;
    }


    public List<DocumentMostView> getListDocumentTop(){
        List<DocumentMostView> listDocumentView = new ArrayList<DocumentMostView>();
        String accKey =  mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME).push().getKey();
        DatabaseReference databaseReference = mDatabase.child(LawDatabaseContract.DocumentEntry.ROOT_NAME);
        Query query = databaseReference;
        query.addValueEventListener(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String key = snapshot.getKey();
                        DocumentMostView doc;
                        String id =dataSnapshot.child(LawDatabaseContract.DocumentEntry.ID_ARM).getValue(String.class);
                        String url =dataSnapshot.child(LawDatabaseContract.DocumentEntry.URL_ARM).getValue(String.class);
                        String title =dataSnapshot.child(LawDatabaseContract.DocumentEntry.TITLE_ARM).getValue(String.class);
                        String description =dataSnapshot.child(LawDatabaseContract.DocumentEntry.DESCRIPTION_ARM).getValue(String.class);
                        int view = dataSnapshot.child(LawDatabaseContract.DocumentEntry.VIEW_ARM).getValue(Integer.class);
                        doc = new DocumentMostView(id,url,title,description,view);
                        listDocumentView.add(doc);
                    }
                }else {
                    Log.d("firebase", "onDataChange: ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "onDataChange: ");
            }
        });
        return listDocumentView;
    }

    public List<DocumentMostView> sortWithViewHighToLow(List<DocumentMostView> list){
        Collections.sort(list, new Comparator<DocumentMostView>() {
            @Override
            public int compare(DocumentMostView a1, DocumentMostView a2) {
                return a2.getView() - a1.getView();
            }
        });

        return list;
    }

    public List<DocumentMostView> sortWithViewLowToHigh(List<DocumentMostView> list){
        Collections.sort(list, new Comparator<DocumentMostView>() {
            @Override
            public int compare(DocumentMostView a1, DocumentMostView a2) {
                return a1.getView() - a2.getView();
            }
        });

        return list;
    }
}
