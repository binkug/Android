package com.example.firstapp;

import android.os.Bundle;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class TabUse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_use);

        //TabHost 찾아오기
        TabHost host = (TabHost)findViewById(R.id.host);
        //탭 설정 시작
        host.setup();

        //첫번째 탭 설정
        TabHost.TabSpec spec = host.newTabSpec("탭1");
        spec.setIndicator(
                null,
                ResourcesCompat.getDrawable(
                        getResources(),
                        R.drawable.tab_icon1,
                        null));
        spec.setContent(R.id.tab1);
        host.addTab(spec);

        //새로운 탭 생성
        spec = host.newTabSpec("탭2");
        //탭 아이콘 설정
        spec.setIndicator(
                null,
                ResourcesCompat.getDrawable(
                        getResources(),
                        R.drawable.tab_icon2,
                        null));
        //탭에 보여질 내용 설정
        spec.setContent(R.id.tab2);
        //탭 추가
        host.addTab(spec);

        spec = host.newTabSpec("탭3");
        //탭 아이콘 설정
        spec.setIndicator(
                null,
                ResourcesCompat.getDrawable(
                        getResources(),
                        R.drawable.tab_icon1,
                        null));
        //탭에 보여질 내용 설정
        spec.setContent(R.id.tab3);
        //탭 추가
        host.addTab(spec);
    }
}