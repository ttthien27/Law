package com.android.Law.adapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.Law.R;
import com.android.Law.models.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DocumentSearchAdapter extends  RecyclerView.Adapter<DocumentSearchAdapter.DocumentSearchViewHolder>{

    private List<Document> documentSearchList;

    public DocumentSearchAdapter(List<Document> documentSearchList) {
        this.documentSearchList = documentSearchList;
    }

    @NonNull
    @Override
    public DocumentSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document_search,parent,false);
        return new DocumentSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentSearchViewHolder holder, int position) {
        //them element cho item
        Document document = documentSearchList.get(position);
        if(document==null)
            return;

        String des = document.getDocDescription().toUpperCase(Locale.ROOT);
        String paraShort = document.getDocParagraphShort();
        String strTerm = document.getDocTermQuery();
        List<String> list = new ArrayList<String>();
        String desChange = "";
        if(des.length()>138){
            desChange = des.substring(0,138) + "...";
            Log.d("Test ... ", "onBindViewHolder: " + desChange);
        }
        else{desChange = des;}

        String[] sb = strTerm.split("@");
        for (String s:sb) {
            if(checkEqual(list,s))
                list.add(s);
        }

        for (String s:list) {
            Log.d("Adapter", "onBindViewHolder: "+s);
            s=s.replaceAll("_"," ");
            String replaceWith = "<span style='background-color:yellow'>"
                    + s + "</span>";
            paraShort = paraShort.replaceAll(s,replaceWith);
        }

        holder.tv_Des.setText(desChange);
        holder.tv_ParaShort.setText(Html.fromHtml(paraShort));
    }

    @Override
    public int getItemCount() {

        if(documentSearchList!=null){
            return documentSearchList.size();
        }
        return 0;
    }

    static boolean checkEqual(List<String> list,String str){
        for (String s: list) {
            if(s.equals(str))
                return false;
        }
        return true;
    }

    public class DocumentSearchViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_Des;
        private TextView tv_ParaShort;
        public DocumentSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Des = itemView.findViewById(R.id.tv_des);
            tv_ParaShort = itemView.findViewById(R.id.tv_paraShort);
        }
    }
}
