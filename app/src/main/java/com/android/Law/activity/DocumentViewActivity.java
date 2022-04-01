package com.android.Law.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.Law.R;
import com.android.Law.models.Document;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class DocumentViewActivity extends AppCompatActivity {

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

        PDFView pdfView = findViewById(R.id.pdfvewer);
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
                });

        /*try {
            FileLoader.deleteWith(this).fromDirectory("PDFFiles", FileLoader.DIR_INTERNAL).deleteAllFiles();
            Log.d("DocumentViewActivity.this", "--------------: Xóa pdf");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DocumentViewActivity.this", "--------------: Ko xóa đc pdf");
        }*/
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
                Toast.makeText(DocumentViewActivity.this,"Thêm theo dõi",Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_dowload:
                Toast.makeText(DocumentViewActivity.this,"Tải về",Toast.LENGTH_LONG).show();
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