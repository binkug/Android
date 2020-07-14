package com.example.firstapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CommonViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_view);

        //TextView 가져오기
        TextView txtmsg = (TextView)findViewById(R.id.txtmsg);
        //assets에 있는 폰트 파일을 이용해서 폰트를 생성
        Typeface tf = Typeface.createFromAsset(getAssets(),"nanumgothic.ttf");
        txtmsg.setTypeface(tf);

    }
}