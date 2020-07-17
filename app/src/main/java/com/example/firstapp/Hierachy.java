package com.example.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Hierachy extends AppCompatActivity {

    //class를 상속받았는데 에러가 나는 경우
    //1.class가 final class 인 경우 - 상속을 할 수 없어서
    //2.class의 생성자가 private 이라서 - 상속을 할 수 없어서
    //3.매개변수가 없는 생성자(default constructor)가 없어서
    //생성자를 직접 만들어서 해결


    //뷰를 직접 생성
    //그림을 그릴 때 이 방법을 선택
    private class MyView extends View {
        //기본 생성자가 없어서 생성자를 직접 호출
        public MyView(Context context){
            super(context);
        }
        //터치를 위한 메소드
        /*
        @Override
        public boolean onTouchEvent(MotionEvent event){
            //상위 클래스의 메소드를 호출
            super.onTouchEvent(event);
            //터치한 좌표를 토스트로 출력
            String msg =
                    String.format("X:%05d Y:%05d",
                            (int)event.getX(),
                            (int)event.getY());
            Toast.makeText(
                    Hierachy.this,
                    msg,
                    Toast.LENGTH_LONG)
                    .show();

            return true;
        } */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        MyView myView = new MyView(this);
        setContentView(myView);

        myView.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(
                    View view, MotionEvent event){
                String msg =
                        String.format("X:%05d Y:%05d",
                                (int)event.getX(),
                                (int)event.getY());
                Toast.makeText(
                        Hierachy.this,
                        msg,
                        Toast.LENGTH_LONG)
                        .show();
                return true;
            }
        });


    }
}