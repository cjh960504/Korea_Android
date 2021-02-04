package com.study.FirstProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //화면에 버튼 나오게 하기
        //Alt + Enter = import
        //생성자매개변수 보기 F2
        Button bt = new Button(this);
        bt.setText("나의 첫 버튼");

        //화면에 부착
        //this.setContentView(레이아웃xml의 경로 및 파일명);
        //-> 구글은 res폴더를 R.java로 관리 
        //res아래 파일들이 추가되면 실시간으로 R.java내에 멤버변수로 관리 
        //상수로 넣어버려서 버리기떄문에 R.layout.linear로 참조가능
        this.setContentView(R.layout.linear);
    }
}