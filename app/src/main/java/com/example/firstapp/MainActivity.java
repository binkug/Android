package com.example.firstapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity의 mainview를 설정하는 문장
        setContentView(R.layout.activity_main);
        //xml 파일에 만든 view 를 찾아오기
        TextView txtCreed = findViewById(R.id.txtcreed);
        //CharSequence는 String의 상위 인터페이스
        txtCreed.setText("이건 된다");
        //텍스트를 가져올 때 Android에서는 toString()을 호출해서 가져옵니다.
        String creed = txtCreed.getText().toString();
        //로그 출력
        Log.e("삶의 신조",creed);

    }
}