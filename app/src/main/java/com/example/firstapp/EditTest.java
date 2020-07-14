package com.example.firstapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditTest extends AppCompatActivity {

    //뷰 변수
    EditText edit;
    TextView text;
    Button btnShow;
    Button btnHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_test);

        //뷰 객체 찾아오기
        edit = (EditText)findViewById(R.id.edit);
        text = (TextView)findViewById(R.id.text);
        btnShow = (Button)findViewById(R.id.btnshow);
        btnHide = (Button)findViewById(R.id.btnhide);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //키보드 관리 객체 가져오기
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                //키보드 출력 : 키보드를 누르면 edit에 입
                imm.showSoftInput(edit,0);
            }
        });
        btnHide.setOnClickListener((View view) -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edit.getWindowToken(),0);
        });

        //edit에 입력 제한
        edit.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(3),
                new InputFilter.AllCaps()
        });

        //edit에 문자열이 변경되면 처리
        edit.addTextChangedListener(
                new TextWatcher() {
                    //키보드는 눌렀고 누른 키보드의 값을
                    //EditText에 표시하기 전
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //edit의 문자열을 text에 출력
                        text.setText(edit.getText());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
    }

}