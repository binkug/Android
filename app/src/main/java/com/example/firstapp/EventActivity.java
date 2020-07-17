package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//Click 이벤트 처리를 위한 클래스
//Click 이벤트 처리를 위한 클래스
//Click 이벤트 처리를 위한 클래스
/*
class EventHandler implements View.OnClickListener{
    String msg;
    public void setMsg(String msg){
        this.msg = msg;
    }
    EventActivity eventActivity;
    //외부에서 문자열을 주입받아서 msg에 대입
    public EventHandler(
            EventActivity eventActivity,
            String msg){
        this.eventActivity = eventActivity;
        this.msg = msg;
    }

    public void onClick(View view){
        Toast.makeText(
                eventActivity,
                msg,
                Toast.LENGTH_SHORT)
                .show();
    }
}
*/

public class EventActivity extends AppCompatActivity {
    String msg = "메시지";

    class EventHandler implements View.OnClickListener{
        public void onClick(View view){
            Toast.makeText(
                    EventActivity.this,
                    msg,
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switch (view.getId()){
                    case R.id.btn1:
                        Toast.makeText(EventActivity.this,"버튼1 클릭",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn2:
                        Toast.makeText(EventActivity.this,"버튼2 클릭",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        //2개 버튼의 클릭 이벤트를 eventHandler 객체 1개가 처리
        //이러한 형태를 이벤트 라우팅이라고 합니다.
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);



    }
}