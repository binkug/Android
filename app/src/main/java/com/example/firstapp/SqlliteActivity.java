package com.example.firstapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SqlliteActivity extends AppCompatActivity {
    //데이터베이스 사용을 위한 변수
    private SQLiteOpenHelper dbHelper;
    //뷰에 대한 참조 변수
    private Button btncreate,btnread,btnupdate,btndelete;
    private TextView txtdisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite);

        //뷰에 대한 참조 생성
        btncreate = (Button)findViewById(R.id.btncreate);
        btnread = (Button)findViewById(R.id.btnread);
        btnupdate = (Button)findViewById(R.id.btnupdate);
        btndelete = (Button)findViewById(R.id.btndelete);
        txtdisplay = (TextView)findViewById(R.id.txtdisplay);

        //데이터베이스 사용을 위한 참조도 생성
        dbHelper = new PersonDBHelper(SqlliteActivity.this);

        Button.OnClickListener buttonHandler = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                //변수를 선언하고 시작
                //데이터베이스 사용을 위한 변수
                SQLiteDatabase db;
                //데이터를 저장하기 위한 변수
                ContentValues row;
                switch (view.getId()){
                    case R.id.btncreate :
                        db = dbHelper.getWritableDatabase();
                        //db.execSQL("insert into person(name,age) values('안중근',32)");
                        //ORM 형태로 데이터 삽입
                        row = new ContentValues();
                        row.put("name","이봉창");
                        row.put("age",37);
                        //데이터 삽입
                        db.insert("person",null,row);

                        dbHelper.close();
                        break;

                    case  R.id.btnread:
                            db = dbHelper.getReadableDatabase();
                            //데이터베이스에서 읽기 작업 수행
                        Cursor cursor = db.rawQuery("select * from person",null);
                        //각 행을 읽어서 하나의 문자열로 만드는 작성
                        StringBuilder sb = new StringBuilder();
                        //행 단위로 검색 결과 읽기
                        while (cursor.moveToNext()){
                            String name = cursor.getString(1);
                            int age = cursor.getInt(2);
                            sb.append(name + " " +age+"\n");
                        }
                        //결과를 문자열로 만들기
                        String msg = sb.toString();

                        if (msg.length()==0){
                            txtdisplay.setText("읽은 데이터 없음");
                        }else{
                            txtdisplay.setText(msg);
                        }
                        //사용한 객체 정
                        cursor.close();
                        dbHelper.close();
                        break;

                    case R.id.btnupdate:

                        db=dbHelper.getWritableDatabase();
                        //변경할 데이터 생성
                        row = new ContentValues();
                        row.put("age",33);
                        //person 테이블에서 name이 이봉창인 데이터를 row로 변경
                        db.update("person",row,"name='이본창'",null);

                        db.close();
                        break;

                    case R.id.btndelete :
                        db = dbHelper.getWritableDatabase();
                        //삭제 구문 실행
                        //delete from person where name = '안중근 '
                        db.delete("person","name='안중근'",null);
                        db.close();
                }
            }
        };
        //버튼과 이벤트 핸들러 연결
        btncreate.setOnClickListener(buttonHandler);
        btnread.setOnClickListener(buttonHandler);
        btnupdate.setOnClickListener(buttonHandler);
        btndelete.setOnClickListener(buttonHandler);

    }
}