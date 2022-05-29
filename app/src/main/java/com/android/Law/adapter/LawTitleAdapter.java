package com.android.Law.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.Law.R;

import java.util.List;

public class LawTitleAdapter extends BaseAdapter {

    private Context context;
    private List<String> listTitle;

    public LawTitleAdapter(Context context, List<String> title) {
        this.context = context;
        this.listTitle = title;
    }

    @Override
    public int getCount() {
        return listTitle != null ? listTitle.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_lawtitle, parent, false);
        TextView txtName = rootView.findViewById(R.id.tv_ItemLawTitle_Name);
        txtName.setText(listTitle.get(position).toString());


        return rootView;
    }
}
