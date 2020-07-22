package com.example.firstapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloadActivity extends AppCompatActivity {
    private Button drawimage,saveimage;
    private ImageView imageview;

    //이미지를 다운로드 받아서 바로 출력할 스레드와 핸들
    class DrawThread extends Thread{
        @Override
        public void run() {
            try {
                //이미지 url 만들기
                URL url = new URL("https://platum.kr/wp-content/uploads/2019/12/VROONG.jpg");
                //이미지의 스트림을 생성
                InputStream is = url.openStream();
                //비트맵으로 변환
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                //핸들러에게 전송
                Message message = new Message();
                message.obj = bitmap;
                drawHandler.sendMessage(message);
            }catch (Exception e){
                Log.e("다운로드 에러",e.getMessage());
            }
        }
    }
    Handler drawHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap)msg.obj;
            imageview.setImageBitmap(bitmap);
        }
    };

    class SaveThread extends Thread{
        @Override
        public void run() {
            try {
                URL url = new URL("https://platum.kr/wp-content/uploads/2019/12/VROONG.jpg");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setUseCaches(false);
                con.setConnectTimeout(30000);

                //바이트 스트림을 생성
                InputStream is = con.getInputStream();
                //다운로드 받은 내용을 저장할 배열을 생성
                byte [] rester = new byte[con.getContentLength()];
                //파일의 내용을 읽어서 rester에 저장하고 파일에 기록
                FileOutputStream fos = openFileOutput("1.png",0);
                while (true){
                    int read = is.read(rester);
                    if(read <= 0){
                        break;
                    }
                    fos.write(rester,0,read);
                }

            }catch (Exception e){
                Log.e("다운로드 에러",e.getMessage());
            }
            Message msg = new Message();
            msg.obj = "1.png";
            saveHandler.sendMessage(msg);
        }
    }

    Handler saveHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            String filename = (String) msg.obj;
            String imagePath = Environment.getDataDirectory().getAbsolutePath()+"/data/com.example.firstapp/files/"+filename;
            imageview.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_download);

        imageview = (ImageView)findViewById(R.id.imageview);
        drawimage = (Button)findViewById(R.id.drawimage);
        saveimage = (Button)findViewById(R.id.saveimage);

        Button.OnClickListener listener =
                new Button.OnClickListener(){
                    public void onClick(View view){
                        switch (view.getId()){
                            case R.id.drawimage:
                                new DrawThread().start();
                                break;
                            case R.id.saveimage:
                                //자신의 data 디렉토리에 이미지 파일이 있는지 확인
                                String imagePath =
                                        Environment.getDataDirectory().getAbsolutePath()
                                                + "/data/com.example.firstapp/files/1.png";
                                //File 객체 생성
                                File file = new File(imagePath);
                                if(file.exists()){
                                    Toast.makeText(ImageDownloadActivity.this,
                                            "파일이 존재", Toast.LENGTH_LONG).show();
                                    //파일의 내용을 출력
                                    imageview.setImageBitmap(BitmapFactory.decodeFile(imagePath));
                                }else{
                                    Toast.makeText(ImageDownloadActivity.this,
                                            "파일이 존재하지 않음", Toast.LENGTH_LONG).show();
                                    //다운로드 받아서 파일로 저장한 후 출력
                                    new SaveThread().start();
                                }

                                break;
                        }
                    }
                };
        drawimage.setOnClickListener(listener);
        saveimage.setOnClickListener(listener);
    }
}