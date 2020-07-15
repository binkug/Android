package com.example.firstapp;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SoundNotice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_notice);

        //버튼 찾아오기
        Button vibrate = (Button)findViewById(R.id.vibrate);
        Button systemsound = (Button)findViewById(R.id.systemsound);
        Button usersound = (Button)findViewById(R.id.usersound);

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(3000);
            }
        });

        systemsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri s1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                //getApplicationContext()는 애플리케이션의 시작 Activity의 참조를 리턴해주는 메소드
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),s1);
                ringtone.play();

            }
        });

        usersound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //여기서 context는 자신이 현재 사용하고 있는 Activity를 적어주면 된다.
                MediaPlayer mediaPlayer = MediaPlayer.create(SoundNotice.this,R.raw.buttoneffect);
                mediaPlayer.start();
            }
        });

    }
}