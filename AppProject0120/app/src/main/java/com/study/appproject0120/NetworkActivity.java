package com.study.appproject0120;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class NetworkActivity extends AppCompatActivity {
    String TAG = this.getClass().getName(); //현재 클래스명
    ConnectionManager manager;
    EditText t_id;
    EditText t_pass;
    EditText t_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        t_id = this.findViewById(R.id.t_id);
        t_pass = this.findViewById(R.id.t_pass);
        t_name = this.findViewById(R.id.t_name);
    }

    //반드시 View를 매개변수로 넣어줘야 onClick핸들러가 작동
    //View는 이벤트를 일으킨 컴포넌트를 의미
    public void regist(View view) {
        Log.d(TAG, "나 눌렀니?");
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"m_id\":\""+t_id.getText()+"\",");
        sb.append("\"m_pass\":\""+t_pass.getText()+"\",");
        sb.append("\"m_name\":\""+t_name.getText()+"\"");
        sb.append("}");
        manager = new ConnectionManager("http://192.168.219.106:8888/rest/member", sb.toString());
        manager.start();
    }
}