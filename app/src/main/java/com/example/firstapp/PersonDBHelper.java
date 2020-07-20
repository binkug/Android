package com.example.firstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonDBHelper extends SQLiteOpenHelper {
    //상위 클래스에 DefaultContsructor가 없어서 생성자를 직접 생성
    public PersonDBHelper(Context context) {
        super(context, "person.db",null ,1);
    }
    //데이터 베이스를 처음 사용할 때 호출되는 메소드
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table person(num INTEGER primary key autoincrement,name TEXT,age INTEGER);");
    }

    //SQLite 또는 App의 버전이 변경된 경우 호출되는 메소
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //기존의 데이터를 제거하고 새로 생성
        sqLiteDatabase.execSQL("drop table person");
        onCreate(sqLiteDatabase);
    }
}
