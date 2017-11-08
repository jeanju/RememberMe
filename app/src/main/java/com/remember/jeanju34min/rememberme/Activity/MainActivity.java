package com.remember.jeanju34min.rememberme.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.remember.jeanju34min.rememberme.Adapter.ListAdapter;
import com.remember.jeanju34min.rememberme.BackPressCloseSystem;
import com.remember.jeanju34min.rememberme.DataBase.DbHelper;
import com.remember.jeanju34min.rememberme.ListViewItem;
import com.remember.jeanju34min.rememberme.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter = new ListAdapter();
    //private ListViewItem[] mlistviewitems = new ListViewItem[0];
    public DbHelper mDbHelper = null;
    private BackPressCloseSystem backPressCloseSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DB 연결
        mDbHelper = new DbHelper(this);
        // List에 DB 내용 하나씩 저장
        mDbHelper.open();
        Log.v("MY_TAG", "db conut = "+mDbHelper.getCount());
        int count = mDbHelper.getCount();
        for(int i = 0; i < count; i++) {
            Log.v("MY_TAG", "db conut = "+mDbHelper.getCount());
            adapter.addItem(mDbHelper.getTitle(i), mDbHelper.getAddress(i), mDbHelper.getID(i));
        }
        mDbHelper.close();
        // 내용이 추가된 List를 ListView에 연결
        ListView mListview = (ListView) findViewById(R.id.wannago_list);
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Get Item (automatically position select)
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;
                // DB에서 URL 가져오기
                int dbID = item.getDbid() ;
                mDbHelper.getURL(dbID);
                // TODO : DB URL info page쪽에 넘기기
                // ListItem 하나 클릭 시 Info Page로 연결
                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);

            }
        }) ;

        // 추가 버튼 구현
        Button  mButton_add = (Button) findViewById(R.id.add_button);
        mButton_add.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(),"리스트추가",Toast.LENGTH_SHORT).show();

                                               Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                                               startActivity(intent);
                                           }
                                       }
        );
        // 삭제 버튼 구현
        Button  mButton_delete = (Button) findViewById(R.id.delete_button);
        mButton_delete.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(),"리스트삭제",Toast.LENGTH_SHORT).show();
                                               //  삭제 화면에서 삭제 버튼을 누를 경우,
                                               if(adapter.getWannaDelete()) {
                                                   // ListItem 중 체크된 것들을 DB와 List에서 삭제
                                                   int count = adapter.getCount();
                                                   mDbHelper.open();
                                                   for(int i = count-1;i >= 0;i--){
                                                       ListViewItem item = (ListViewItem)adapter.getItem(i);
                                                       if(item.getChecked()) {
                                                           Log.v("MY_TAG", count+" : Count "+ i + " : position "+ item.getDbid() +" : DB _ID");
                                                           mDbHelper.delete(item.getDbid());
                                                           adapter.deleteItem(i);
                                                           //i--;
                                                       }
                                                   }
                                                   mDbHelper.close();
                                                   adapter.notifyDataSetChanged();
                                                   adapter.setWannaDelete(false);
                                                   // 추가 버튼 활성화
                                                   Button  mButton_add = (Button) findViewById(R.id.add_button);
                                                   mButton_add.setEnabled(true);
                                               }
                                               else {
                                                   // 기본 화면에서 삭제 버튼을 누를 경우,
                                                   adapter.setWannaDelete(true);
                                                   adapter.notifyDataSetChanged();
                                                   // 추가 버튼 비활성화
                                                   Button mButton_add = (Button) findViewById(R.id.add_button);
                                                   mButton_add.setEnabled(false);
                                               }

                                           }
                                       }
        );

        Log.v("MY_TAG", "test");
        // Back Key 두번으로 앱 종료하는 시스템 수행
        backPressCloseSystem = new BackPressCloseSystem(this);
    }

    @Override
    public void onBackPressed() {
        if(adapter.getWannaDelete()){
            // 삭제 화면에서 Back Key 누를 경우,
            adapter.setWannaDelete(false);
            for (int i = 0; i < adapter.getCount() ; i++){
                ((ListViewItem)adapter.getItem(i)).setChecked(false);
            }
            adapter.notifyDataSetChanged();
            Button  mButton_add = (Button) findViewById(R.id.add_button);
            mButton_add.setEnabled(true);
        }
        else {
            // 기본 화면에서 Back Key 누를 경우,
            backPressCloseSystem.onBackPressed();
        }
    }
}
