package com.android.Law.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.Law.R;
import com.android.Law.activity.DocumentViewActivity;
import com.android.Law.models.Document;
import com.android.Law.models.DocumentMostView;
import com.android.Law.models.DocumentNew;

import java.util.List;
import java.util.Locale;

public class DocumentNewAdapter extends  RecyclerView.Adapter<DocumentNewAdapter.DocumentNew_ViewHolder>{
    private Context mContext;
    private List<DocumentNew> documentList;

    public DocumentNewAdapter(Context context,List<DocumentNew> documentList) {
        this.mContext = context;
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public DocumentNew_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document_new,parent,false);
        return new DocumentNewAdapter.DocumentNew_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentNew_ViewHolder holder, int position) {
        DocumentNew document = documentList.get(position);
        if(document==null)
            return;
        //holder.BindColor();
        Log.d("-----------------------", String.valueOf(position));
        //holder.tv_Num.setText(String.valueOf(position));
        String des = document.getDocDescription().toUpperCase(Locale.ROOT);
        String title = document.getDocTitle();
        String desChange = "";
        if(des.length()>138){
            desChange = des.substring(0,138) + "...";
            Log.d("Test ... ", "onBindViewHolder: " + desChange);
        }
        else{desChange = des;}

        holder.tv_Name.setText(desChange);
        if(title.equals("THÔNG TƯ LIÊN TỊCH")){title="THÔNG TƯ";}
        holder.tv_Title.setText(title);
        holder.tv_Date.setText("Ngày ban hành: "+document.getDate());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(document);
            }
        });
    }

    private void onClickGoToDetail(Document document){
        Intent intent = new Intent(mContext, DocumentViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("document",document);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(documentList!=null){
            return documentList.size();
        }
        return 0;
    }

    public class DocumentNew_ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_Name;
        private TextView tv_Title;
        private TextView tv_Date;
        private CardView cardView;
        private LinearLayout linearLayout;
        private ImageView imageView;
        public DocumentNew_ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_ItemDocNew_Des);
            tv_Title = itemView.findViewById(R.id.tv_ItemDocNew_Title);
            tv_Date = itemView.findViewById(R.id.tv_ItemDocNew_Date);
            //cardView = itemView.findViewById(R.id.tv_ItemDocMostView_Des);
            linearLayout = itemView.findViewById(R.id.layout_ItemDocmentNew);
            //imageView = itemView.findViewById(R.id.imgView_item);
        }

    }
}
