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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.Law.Data.LawSQLiteDao;
import com.android.Law.R;
import com.android.Law.activity.DocumentViewActivity;
import com.android.Law.models.Document;
import com.chauthai.swipereveallayout.SwipeRevealLayout;

import java.util.List;
import java.util.Locale;

public class DocumentFollowAdapter extends RecyclerView.Adapter<DocumentFollowAdapter.DocumentFollowViewHolder> {
    private Context mContext;
    private List<Document> documentList;
    private LawSQLiteDao sqliteDAO;


    public DocumentFollowAdapter(Context context, List<Document> documentList) {
        this.mContext = context;
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public DocumentFollowAdapter.DocumentFollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document_follow, parent, false);
        return new DocumentFollowAdapter.DocumentFollowViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DocumentFollowAdapter.DocumentFollowViewHolder holder, int position) {
        Document document = documentList.get(position);
        sqliteDAO = new LawSQLiteDao(mContext, false);
        if (document == null)
            return;
        //holder.BindColor();
        Log.d("-----------------------", String.valueOf(position));
        //holder.tv_Num.setText(String.valueOf(position));
        String des = document.getDocDescription().toUpperCase(Locale.ROOT);
        String title = document.getDocTitle();
        String desChange = "";
        if (des.length() > 138) {
            desChange = des.substring(0, 138) + "...";
            Log.d("Test ... ", "onBindViewHolder: " + desChange);
        } else {
            desChange = des;
        }

        holder.tv_Name.setText(desChange);
        if (title.equals("THÔNG TƯ LIÊN TỊCH")) {
            title = "THÔNG TƯ";
        }
        holder.tv_Title.setText(title);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(document);
            }
        });

        holder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UnFollow", "onClick");
                boolean check = sqliteDAO.deleteDocumentFollow(mContext,document.getDocId());
                if(check) {
                    documentList.remove(position);
                    notifyItemRemoved(position);
                }else {
                    Toast.makeText(mContext, "Bỏ theo dõi không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void onClickGoToDetail(Document document) {
        Intent intent = new Intent(mContext, DocumentViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("document", document);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (documentList != null) {
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

    public class DocumentFollowViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_Name;
        private TextView tv_Title;
        private CardView cardView;
        private LinearLayout linearLayout;
        private LinearLayout deleteLayout;
        private ImageView imageView;

        public DocumentFollowViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_ItemFollow_name);
            tv_Title = itemView.findViewById(R.id.tv_ItemFollow_title);
            //cardView = itemView.findViewById(R.id.cv_item);
            linearLayout = itemView.findViewById(R.id.layout_ItemDocmentFollow);
            deleteLayout = itemView.findViewById(R.id.layout_DeleteDocmentFollow);
            //imageView = itemView.findViewById(R.id.imgView_item);
        }

        void BindColor() {
            if (getAdapterPosition() % 2 == 0) {
                cardView.setCardBackgroundColor(Color.parseColor("#43c6ed"));
                imageView.setImageResource(R.drawable.c1);
            } else {
                cardView.setCardBackgroundColor(Color.parseColor("#43ede1"));
                imageView.setImageResource(R.drawable.c2);
            }
        }
    }
}
