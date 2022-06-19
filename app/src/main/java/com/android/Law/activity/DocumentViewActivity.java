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
import com.android.Law.models.Document;
import com.krishna.fileloader.FileLoader;

public class DocumentViewActivity extends AppCompatActivity {

    private Document documentInView;
    private LawSQLiteDao sqliteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_view);

        /*WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://drive.google.com/file/d/1m4qWzuiLnbkTJTAO12_j5YQSCuqM4cXp/view?usp=sharing");
        //webView.loadUrl("https://drive.google.com/file/d/1m4qWzuiLnbkTJTAO12_j5YQSCuqM4cXp/view?usp=viewer");*/

        /*PDFView pdfView = findViewById(R.id.pdfvewer);
        ProgressBar progressBar = findViewById(R.id.progressbar);
        Toolbar toolbar = findViewById(R.id.toolbar_DocumentView);
        setSupportActionBar(toolbar);

        progressBar.setVisibility(View.VISIBLE);

        FileLoader.with(this)
                .load("https://dl.dropboxusercontent.com/s/k5lacsmizip7ze1/24_QD-UBQGCDS_505327.pdf?dl=0")
                .fromDirectory("PDFFiles",FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        progressBar.setVisibility(View.GONE);

                        File pdfFile = response.getBody();
                        pdfView.fromFile(pdfFile)
                                .password(null)
                                .defaultPage(0)
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .onDraw((canvas, pageWidth, pageHeight, displayedPage) -> {

                                })
                                .onDrawAll((canvas, pageWidth, pageHeight, displayedPage) -> {

                                })
                                .onPageError((page, t1) -> {
                                    Toast.makeText(DocumentViewActivity.this,"Lỗi"+page,Toast.LENGTH_SHORT).show();
                                })
                                .onPageChange(new OnPageChangeListener() {
                                    @Override
                                    public void onPageChanged(int page, int pageCount) {

                                    }
                                })
                                .onTap(new OnTapListener() {
                                    @Override
                                    public boolean onTap(MotionEvent e) {
                                        return true;
                                    }
                                })
                                .onRender(new OnRenderListener() {
                                    @Override
                                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                                        pdfView.fitToWidth();
                                    }
                                })
                                .enableAnnotationRendering(true)
                                .invalidPageColor(Color.WHITE)
                                .load();
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Toast.makeText(DocumentViewActivity.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);


                    }
                });*/

        /*try {
            FileLoader.deleteWith(this).fromDirectory("PDFFiles", FileLoader.DIR_INTERNAL).deleteAllFiles();
            Log.d("DocumentViewActivity.this", "--------------: Xóa pdf");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DocumentViewActivity.this", "--------------: Ko xóa đc pdf");
        }*/

        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }

        documentInView = (Document) bundle.get("document");
        sqliteDAO = new LawSQLiteDao(DocumentViewActivity.this, false);

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