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

import java.util.List;
import java.util.Locale;

public class DocumentMostViewAdapter extends  RecyclerView.Adapter<DocumentMostViewAdapter.DocumentMostView_ViewHolder>{
    private Context mContext;
    private List<DocumentMostView> documentList;


    public DocumentMostViewAdapter(Context context,List<DocumentMostView> documentList) {
        this.mContext = context;
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public DocumentMostView_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document_mostview,parent,false);
        return new DocumentMostView_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentMostView_ViewHolder holder, int position) {
        DocumentMostView document = documentList.get(position);
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
        holder.tv_View.setText("Lượt xem: "+document.getView());
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

    /*@Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }*/

    public class DocumentMostView_ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_Name;
        private TextView tv_Title;
        private TextView tv_View;
        private CardView cardView;
        private LinearLayout linearLayout;
        private ImageView imageView;
        public DocumentMostView_ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_ItemDocMostView_Des);
            tv_Title = itemView.findViewById(R.id.tv_ItemDocMostView_Title);
            tv_View = itemView.findViewById(R.id.tv_ItemDocMostView_View);
            //cardView = itemView.findViewById(R.id.tv_ItemDocMostView_Des);
            linearLayout = itemView.findViewById(R.id.layout_ItemDocmentMostView);
            //imageView = itemView.findViewById(R.id.imgView_item);
        }

    }
}
