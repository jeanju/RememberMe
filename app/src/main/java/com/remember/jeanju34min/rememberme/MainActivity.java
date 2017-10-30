package com.remember.jeanju34min.rememberme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3","LIST4","","","","","","","","","","","","","",""} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView mListview = (ListView) findViewById(R.id.wannago_list);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.wannago_Name, LIST_MENU) ;
        mListview.setAdapter(adapter) ;

        Button  mButton_add = (Button) findViewById(R.id.button1);
        mButton_add.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(),"리스트추가",Toast.LENGTH_SHORT).show();
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
