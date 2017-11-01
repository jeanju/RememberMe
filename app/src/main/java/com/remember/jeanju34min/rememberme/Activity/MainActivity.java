package com.remember.jeanju34min.rememberme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.remember.jeanju34min.rememberme.Adapter.ListAdapter;
import com.remember.jeanju34min.rememberme.ListViewItem;
import com.remember.jeanju34min.rememberme.R;

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
                // Get Item (automatically position select)
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

//                String titleStr = item.getTitle() ;
//                String descStr = item.getAddr() ;

                // Move to Info Page
                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);

            }
        }) ;

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
        Button  mButton_delete = (Button) findViewById(R.id.delete_button);
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
