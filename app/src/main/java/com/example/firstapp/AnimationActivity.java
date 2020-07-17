package com.example.firstapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        if(savedInstanceState !=null){
            Log.e("Data",savedInstanceState.getString("song"));
        }

        final ImageView imageView =
                (ImageView)findViewById(R.id.imageview);
        Button button =
                (Button)findViewById(R.id.animationbtn);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Animation rotate =
                        AnimationUtils.loadAnimation(
                                getApplicationContext(),
                                R.anim.rotate);
                imageView.startAnimation(rotate);
                rotate.setAnimationListener(
                        new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                Toast.makeText(
                                        AnimationActivity.this,
                                        "애니메이션 종료",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
            }
        });

    }
    //회전이 발생하면 호출되는 메소드
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        setContentView(R.layout.activity_animation);
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        //어떤 데이터를 저장하면 onCreate에서 사용 가능
        bundle.putString("song","spice girls");

    }
}