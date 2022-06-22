package com.android.Law.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.Law.Data.LawSQLiteDao;
import com.android.Law.R;
import com.android.Law.firebase.DocumentRequester;
import com.android.Law.firebase.LawDatabaseContract;
import com.android.Law.firebase.UserAccountsRequester;
import com.android.Law.models.Document;
import com.android.Law.models.DocumentMostView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.krishna.fileloader.FileLoader;

import java.util.ArrayList;
import java.util.List;

public class DocumentViewActivity extends AppCompatActivity {

    private Document documentInView;
    private LawSQLiteDao sqliteDAO;
    private DocumentRequester documentRequester;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_view);


        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }

        documentInView = (Document) bundle.get("document");
        sqliteDAO = new LawSQLiteDao(DocumentViewActivity.this, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://appphapluat-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference();
        List<DocumentMostView> listDocumentViewTop = new ArrayList<DocumentMostView>();
        DatabaseReference databaseReference = myRef.child(LawDatabaseContract.DocumentEntry.ROOT_NAME_TOP);
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
                        int view = dataSnapshot.child(LawDatabaseContract.DocumentEntry.VIEW_ARM).getValue(int.class);
                        doc = new DocumentMostView(id,url,title,description,view);
                        listDocumentViewTop.add(doc);
                    }
                    documentRequester = new DocumentRequester();
                    documentRequester.updateUserAccount(new DocumentMostView(
                            documentInView.getDocId(),documentInView.getDocUrl(),documentInView.getDocTitle(),documentInView.getDocDescription(),0
                    ));
                    Log.d("firebase", "onDataChange: ");
                }else {
                    Log.d("firebase", "onDataChange: ");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "onDataChange: ");
            }
        });



        if(!LawSQLiteDao.checkExistDocumentSeen(DocumentViewActivity.this,documentInView.getDocId())){
            boolean result = LawSQLiteDao.addDocumentSeen(DocumentViewActivity.this,documentInView);
            if (result){
                //Toast.makeText(DocumentViewActivity.this,"Đã thêm theo dõi",Toast.LENGTH_LONG).show();
                Log.d("DocumentViewActivity", "onOptionsItemSelected: Them Seen: "+ result);
            }else{
                Log.d("DocumentViewActivity", "onOptionsItemSelected: Them Seen: "+ result);
                Toast.makeText(DocumentViewActivity.this,"Lỗi khi thêm danh sách đã xem",Toast.LENGTH_LONG).show();
            }
        }else{
            boolean check = sqliteDAO.deleteDocumentSeen(DocumentViewActivity.this,documentInView.getDocId());
            if(check){
                boolean result = LawSQLiteDao.addDocumentSeen(DocumentViewActivity.this,documentInView);
                if (result){
                    //Toast.makeText(DocumentViewActivity.this,"Đã thêm theo dõi",Toast.LENGTH_LONG).show();
                    Log.d("DocumentViewActivity", "onOptionsItemSelected: Them Seen: "+ result);
                }else{
                    Log.d("DocumentViewActivity", "onOptionsItemSelected: Them Seen: "+ result);
                    Toast.makeText(DocumentViewActivity.this,"Lỗi khi thêm danh sách đã xem",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(DocumentViewActivity.this,"Lỗi khi thêm danh sách đã xem",Toast.LENGTH_LONG).show();
            }
        }





        ProgressBar progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        Toolbar toolbar = findViewById(R.id.toolbar_DocumentView);
        setSupportActionBar(toolbar);
        WebView webView = findViewById(R.id.webviewer);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(documentInView.getDocUrl());
        //webView.loadUrl("https://drive.google.com/file/d/1m4qWzuiLnbkTJTAO12_j5YQSCuqM4cXp/view?usp=viewer");
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_two,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_follow:
                if(!LawSQLiteDao.checkExistDocumentFollow(DocumentViewActivity.this,documentInView.getDocId())){
                    boolean result = LawSQLiteDao.addDocumentFollow(DocumentViewActivity.this,documentInView);
                    if (result){
                        Toast.makeText(DocumentViewActivity.this,"Đã thêm theo dõi",Toast.LENGTH_LONG).show();
                    }else
                        //Log.d("DocumentViewActivity", "onOptionsItemSelected: Them Follow: "+ result);
                        Toast.makeText(DocumentViewActivity.this,"Chưa thêm theo dõi",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DocumentViewActivity.this,"Văn bản đã được theo dõi",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.menu_dowload:
                //Toast.makeText(DocumentViewActivity.this,"Tải về",Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
             try {
            FileLoader.deleteWith(this).fromDirectory("PDFFiles", FileLoader.DIR_INTERNAL).deleteAllFiles();
            Log.d("DocumentViewActivity.this", "--------------: Xóa pdf");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DocumentViewActivity.this", "--------------: Ko xóa đc pdf");
        }
    }
}