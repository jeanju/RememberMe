package com.remember.jeanju34min.rememberme;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jeanju34.min on 2017-10-31.
 */

public class ListAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> mlistViewItemList = new ArrayList<ListViewItem>() ;


    public ListAdapter(){

    }

    @Override
    public int getCount() {
        return mlistViewItemList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return mlistViewItemList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.wannago_Name);
        TextView addrView = (TextView) convertView.findViewById(R.id.wannago_Addr);

        nameView.setText(mlistViewItemList.get(pos).getTitle());
        addrView.setText(mlistViewItemList.get(pos).getAddr());

        return convertView;
    }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(String title, String desc) {
            ListViewItem item = new ListViewItem();

            item.setTitle(title);
            item.setDesc(desc);

            mlistViewItemList.add(item);
        }

    }

