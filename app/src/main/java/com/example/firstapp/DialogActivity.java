package com.example.firstapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {
    Boolean result = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button dlgShow = (Button)findViewById(R.id.dlgshow);
        Button alertDlg = (Button)findViewById(R.id.alertdialog);

        dlgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //대화상자 생성
                Dialog dlg = new Dialog(DialogActivity.this);

                TextView textView = new TextView(DialogActivity.this);
                textView.setText("대화 상자 출력");

                //모양 설정
                dlg.setContentView(textView);
                dlg.setTitle("대화상자");
                //출력
                dlg.show();
            }
        });
        alertDlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                AlertDialog.Builder dlg = new AlertDialog.Builder(DialogActivity.this);
                dlg.setTitle("제목")
                        //지금 처럼 메소드를 연속적으로 호출 하는 것을 메소드 체이닝이라고 한다.
                        //메소드 체이닝을 할 수 있도록 만드는 경우는 어떤 작업을 할 때 설정해야 할 내용이 많고 그 내용들이
                        //필수가 아니고 선택적으로 설정해야 하는 경우 한번에 설정하도록 하면 순서를 기억해야 하거나 종류들을
                        //전부 기억해야 하기 때문에 체이닝 형태로 설정할 수 있도록 만들어 줍니다.
                        .setCancelable(false)
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result = false;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result = true;
                            }
                        })
                        .setMessage("내용")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });


    }
}