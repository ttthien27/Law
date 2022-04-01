package com.android.Law.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.Law.R;
import com.android.Law.models.Document;

import java.util.List;
import java.util.Locale;

public class DocumentAdapter extends  RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>{

    private List<Document> documentList;

    public DocumentAdapter(List<Document> documentList) {
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

        Log.d("-----------------------", String.valueOf(position));
        //holder.tv_Num.setText(String.valueOf(position));
        String des = document.getDocDescription().toUpperCase(Locale.ROOT);
        String desChange = "";
        if(des.length()>65){
            desChange = des.substring(0,65) + "...";
            Log.d("Test ... ", "onBindViewHolder: " + desChange);
        }
        else{desChange = des;}

        holder.tv_Name.setText(desChange);

    }

    @Override
    public int getItemCount() {
        if(documentList!=null){
            return documentList.size();
        }
        return 0;
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_Name;
        private TextView tv_Num;
        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_name);
            //tv_Num = itemView.findViewById(R.id.tv_num);
        }
    }
}
