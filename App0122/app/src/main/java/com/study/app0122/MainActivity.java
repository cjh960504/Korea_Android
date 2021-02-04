package com.study.app0122;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//이 메서드 자체가 이미 inflation 되어 있다.
        LinearLayout wrapper = (LinearLayout)this.findViewById(R.id.wrapper);
        //재사용하기위해 미리 정의해놓은 레이아웃파일을 인플레이션 시켜본다
        //inflation이란?? xml에서 정의해놓은 태그들을 실제 안드로이드 객체로
        LayoutInflater layoutInflater = this.getLayoutInflater();
        layoutInflater.inflate(R.layout.profile_item, wrapper);
        layoutInflater.inflate(R.layout.profile_item, wrapper);
        layoutInflater.inflate(R.layout.profile_item, wrapper);

    }
}