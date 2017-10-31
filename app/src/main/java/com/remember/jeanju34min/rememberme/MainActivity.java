package com.remember.jeanju34min.rememberme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter = new ListAdapter();
    private ListViewItem[] mlistviewitems = new ListViewItem[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView mListview = (ListView) findViewById(R.id.wannago_list);

        adapter.addItem("LIST1", "가산동") ;
        // 두 번째 아이템 추가.
        adapter.addItem("LIST2", "김포시 운양동") ;
        // 세 번째 아이템 추가.
        adapter.addItem("LIST3", "마곡동") ;

        mListview.setAdapter(adapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getAddr() ;

                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);
                // TODO : use item data.
            }
        }) ;

        Button  mButton_add = (Button) findViewById(R.id.button1);
        mButton_add.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(),"리스트추가",Toast.LENGTH_SHORT).show();
                                               adapter.addItem(
                                                       "LIST3", "마곡동") ;

                                           }
                                       }
        );
        Button  mButton_delete = (Button) findViewById(R.id.button2);
        mButton_delete.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(),"리스트삭제",Toast.LENGTH_SHORT).show();
                                           }
                                       }
        );

        //setContentView(example);
        Log.v("MY_TAG", "textview");

    }
}
