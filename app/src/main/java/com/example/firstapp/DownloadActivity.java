package com.example.firstapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {
    private Button naverDownload;
    private TextView naverHtml;

    Thread th= new Thread(){
        @Override
        public void run() {
            //핸들러에게 전달할 데이터를 저장할 객체
            Message message = new Message();
            //데이터 가져오기
            try {
                URL url = new URL("https://m.naver.com");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                StringBuilder sb = new StringBuilder();
                while(true){
                    String line = br.readLine();
                    if(line == null){
                        break;
                    }
                    sb.append(line + "\n");
                }
                String html = sb.toString();
                //데이터 저장
                message.obj = html;

                br.close();
                con.disconnect();
            }catch (Exception e){

            }
            handler.sendMessage(message);
        }
    };

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message obj) {
            //데이터 읽어오기
            String html = (String) obj.obj;
            //데이터 출력
            naverHtml.setText(html);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        naverDownload = (Button)findViewById(R.id.naverdownload);
        naverHtml = (TextView)findViewById(R.id.naverhtml);

        naverDownload.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                th.start();
            }
        });
    }
}