package com.remember.jeanju34min.rememberme.Adapter;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.remember.jeanju34min.rememberme.ListViewItem;
import com.remember.jeanju34min.rememberme.R;

import java.util.ArrayList;

/**
 * Created by jeanju34.min on 2017-10-31.
 */

public class ListAdapter extends BaseAdapter {

    static ViewGroup parents;

    private ArrayList<ListViewItem> mlistViewItemList = new ArrayList<ListViewItem>() ;
    private boolean wannaDelete = false;

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
        parents = parent;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        final ListViewItem Item = mlistViewItemList.get(pos);
        TextView nameView = (TextView) convertView.findViewById(R.id.wannago_Name);
        TextView addrView = (TextView) convertView.findViewById(R.id.wannago_Addr);
        CheckBox deleteBox = (CheckBox) convertView.findViewById(R.id.delete_Check);

        nameView.setText(Item.getTitle());
        addrView.setText(Item.getAddr());

        if (wannaDelete){
            deleteBox.setVisibility(View.VISIBLE);
        }
        else{
            deleteBox.setVisibility(View.GONE);
        }
        if (Item.getChecked()){
            deleteBox.setChecked(true);
        }
        else{
            deleteBox.setChecked(false);
        }

        deleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("MY_TAG", isChecked+" : isChecked");
                mlistViewItemList.get(pos).setChecked(isChecked);
                // checkBox.setChecked(isChecked);
            }
        });

        return convertView;
    }

    // delete page인지 확인
    public void setWannaDelete(boolean set){
        wannaDelete = set;
    }

    public Boolean getWannaDelete(){
        return wannaDelete;
    }


    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(String title, String desc) {
            ListViewItem item = new ListViewItem();

            item.setTitle(title);
            item.setDesc(desc);

            mlistViewItemList.add(item);
        }
/*
        public void visibleCheckbox(){
            //Visible Checkbox
            final Context context = parents.getContext();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View convertView = inflater.inflate(R.layout.list_item, parents, false);


            View b = convertView.findViewById(R.id.checkBox_Layout);
            b.setVisibility(View.VISIBLE);
        }
*/
    }

