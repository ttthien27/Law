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

import java.util.List;
import java.util.Locale;


public class DocumentAdapter extends  RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>{

    private Context mContext;
    private List<Document> documentList;


    public DocumentAdapter(Context context,List<Document> documentList) {
        this.mContext = context;
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document,parent,false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        Document document = documentList.get(position);
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
        holder.tv_Title.setText(title);
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

    public class DocumentViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_Name;
        private TextView tv_Title;
        private CardView cardView;
        private LinearLayout linearLayout;
        private ImageView imageView;
        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_name);
            tv_Title = itemView.findViewById(R.id.tv_title);
            cardView = itemView.findViewById(R.id.cv_item);
            linearLayout = itemView.findViewById(R.id.layout_ItemDocment);
            //imageView = itemView.findViewById(R.id.imgView_item);
        }
        void BindColor(){
            if(getAdapterPosition() % 2 == 0){
                cardView.setCardBackgroundColor(Color.parseColor("#43c6ed"));
                imageView.setImageResource(R.drawable.c1);
            }else {
                cardView.setCardBackgroundColor(Color.parseColor("#43ede1"));
                imageView.setImageResource(R.drawable.c2);
            }
        }
    }
}
