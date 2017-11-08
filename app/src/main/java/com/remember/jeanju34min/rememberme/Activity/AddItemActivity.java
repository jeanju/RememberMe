package com.remember.jeanju34min.rememberme.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.remember.jeanju34min.rememberme.DataBase.DbHelper;
import com.remember.jeanju34min.rememberme.R;

/**
 * Created by jeanju34.min on 2017-11-01.
 */

public class AddItemActivity extends AppCompatActivity {

    private DbHelper mDbHelper;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.add_item);

        Button mButton_add = (Button) findViewById(R.id.finish_info_button);
        mButton_add.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               EditText title_input = findViewById(R.id.input_title);
                                               EditText address_input = findViewById(R.id.input_address);

                                               String title = title_input.getText().toString();
                                               String address = address_input.getText().toString();

                                               if (title.length() != 0 && address.length() != 0) {

                                                   mDbHelper = new DbHelper(context);
                                                   mDbHelper.open();

                                                   // TODO : longitude, latitude, URL 확인하여 입력해주어야 함.
                                                   mDbHelper.insert(title, address, 10, 10, "http://m.daum.net");

                                                   mDbHelper.close();

                                                   Toast.makeText(getApplicationContext(), "리스트에 추가되었습니다.", Toast.LENGTH_SHORT).show();

                                                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                   startActivity(intent);

                                               }
                                           }
                                       }
        );

    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
