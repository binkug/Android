package com.example.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileTextView extends AppCompatActivity {
    //디자인 한 뷰들의 참조를 저장할 변수 선언
    private Button resread,filewirte,fileread,filedelete;
    private EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_text_view);
        //xml 파일에 디자인한 뷰 찾아오기
        resread = (Button) findViewById(R.id.resread);
        filewirte = (Button) findViewById(R.id.filewirte);
        fileread = (Button) findViewById(R.id.fileread);
        filedelete = (Button) findViewById(R.id.filedelete);
        content = (EditText)findViewById(R.id.content);

        Button.OnClickListener clickHandler = new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.resread :
                        //creed.txt 파일의 내용을 읽어서 content에 출력
                        //creed.txt 파일은 raw 디렉토리에 존재

                        try {
                            InputStream fis = getResources().openRawResource(R.raw.creed);
                            byte [] data = new byte[0];
                            //파일의 내용을 한번에 전부 읽기
                            data = new byte[fis.available()];
                            fis.read(data);
                            fis.close();;
                            //읽어온 데이터를 문자열로 변환
                            String msg = new String(data);
                            //출력
                            content.setText(msg);
                        } catch (IOException e) {
                            Log.e("파일 읽기 에러",e.getMessage());
                            e.printStackTrace();
                        }

                        break;
                    case R.id.filewirte :
                            try {
                                //기록할 파일의 경로를 생성
                                FileOutputStream fos = openFileOutput("data.txt", Context.MODE_PRIVATE);
                                //내용을 입력
                                fos.write(content.getText().toString().getBytes());
                                fos.close();

                            }catch (Exception e){
                                Log.e("파일 쓰기  에러",e.getMessage());
                            }
                        break;
                    case R.id.fileread :
                            try {
                                FileInputStream fis = openFileInput("data.txt");

                                byte [] b = new byte[fis.available()];
                                fis.read(b);
                                String d= new String(b);
                                content.setText(d);
                                fis.close();

                            }catch (Exception e){
                                Log.e("파일 읽기 에러",e.getMessage());
                            }
                        break;
                    case R.id.filedelete :
                        deleteFile("data.txt");
                        content.setText("파일 삭제");

                        break;
                }
            }
        };
        //버튼과 이벤트 핸들러 연결
        resread.setOnClickListener(clickHandler);
        filewirte.setOnClickListener(clickHandler);
        fileread.setOnClickListener(clickHandler);
        filedelete.setOnClickListener(clickHandler);


    }
}