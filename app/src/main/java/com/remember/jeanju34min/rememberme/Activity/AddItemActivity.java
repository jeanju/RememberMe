package com.remember.jeanju34min.rememberme.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.remember.jeanju34min.rememberme.DataBase.DbOpenHelper;
import com.remember.jeanju34min.rememberme.R;

/**
 * Created by jeanju34.min on 2017-11-01.
 */

public class AddItemActivity extends AppCompatActivity {

    private DbOpenHelper mDbOpenHelper;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.add_item);

        Button mButton_add = (Button) findViewById(R.id.finish_info_button);
        mButton_add.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Toast.makeText(getApplicationContext(),"리스트에 추가되었습니다.",Toast.LENGTH_SHORT).show();

                                               //TODO: DB에 입력된 내용 추가

                                               mDbOpenHelper = new DbOpenHelper(context);
                                               mDbOpenHelper.open();

                                               mDbOpenHelper.insert("김태희","01000001111" , 10,10);

                                               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                               startActivity(intent);
                                           }
                                       }
        );

    }
}
