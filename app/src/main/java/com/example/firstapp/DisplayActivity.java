package com.example.firstapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    String msg = "기본값";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        final TextView display = (TextView)findViewById(R.id.display);

//        try {
//            //1초에 출력 것 같지만 실제로는 모아서 출력하기 때문에 마지막에 10만 출력이됩니다.
//            for (int i=1;i<=10;i=i+1){
//                Thread.sleep(1000);
//                Log.e("i",i+"");
//                displaybtn.setText("i= "+i);
//            }
//        }catch (Exception e){
//
//        }
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(DisplayActivity.this);

                dlg.setTitle("대화상자 콜백")
                        .setMessage("대화상자는 어떻게 동작")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                msg = "확인";
                                try {

                                    for (int j=1;j<=10;j=j+1){
                                    Thread.sleep(1000);
                                    Log.e("j",j+"");
                                    display.setText("j= "+j);
                                    }
                                }catch (Exception e){}
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                msg="취소";
                            }
                        })
                        .show();

                        //대화 상자가 출력됨과 동시에 수행됩니다.
                        //대화상자가 닫히고 난 후 수행하는 코드를 만들려면
                        //대화상자의 콜백 메소드를 이용해야 합니다.
                        display.setText(msg);
            }
        });
    }
}