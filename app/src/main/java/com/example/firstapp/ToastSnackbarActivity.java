package com.example.firstapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ToastSnackbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_snackbar);

        Button btn = (Button)findViewById(R.id.toastdisp);
        //버튼 클릭했을 때 처리
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //토스트 만들기
                /*Toast toast = Toast.makeText(
                        ToastSnackbarActivity.this,
                        "토스트를 출력합니다.",
                        Toast.LENGTH_LONG);
                //토스트를 화면에 출력
                toast.show();*/

                //스낵바 출력
                Snackbar.make(view,"스낵바출력",Snackbar.LENGTH_LONG).setAction("SONG", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer player = MediaPlayer.create(ToastSnackbarActivity.this,R.raw.buttoneffect);
                        player.start();
                    }
                }).show();

            }
        });


    }
}