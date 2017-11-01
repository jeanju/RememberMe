package com.remember.jeanju34min.rememberme.DataBase;

import android.provider.BaseColumns;

/**
 * Created by jeanju34.min on 2017-11-01.
 */

public final class WannagoDB {

        public static final class CreateDB implements BaseColumns {
            public static final String TITLE = "title";
            public static final String ADDRESS = "address";
            public static final String LNG = "longitude";
            public static final String LAT = "latitude";
            public static final String _TABLENAME = "Wannago";
            public static final String _CREATE =
                    "create table "+_TABLENAME+"("
                            +_ID+" integer primary key autoincrement, "
                            +TITLE+" text not null , "
                            +ADDRESS+" text not null , "
                            +LNG+" Long DEFAULT 0 ,"
                            +LAT+" Long DEFAULT 0);";
        }
}
