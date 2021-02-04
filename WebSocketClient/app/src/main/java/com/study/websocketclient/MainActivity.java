package com.study.websocketclient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //웹소켓 객체 선언
    MyWebSocketClient myWebSocketClient;
    BoardDAO boardDAO;
    ListView listView;
    BoardAdapter boardAdapter;
    Handler handler;
    DetailDialog detailDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        boardAdapter  = new BoardAdapter(this);
        listView.setAdapter(boardAdapter); //리스트뷰와 어댑터 연결!!!

        handler = new Handler(Looper.getMainLooper()){
            //쓰레드들의 부탁을 받아, 대신 UI제어!!
            public void handleMessage(@NonNull Message message) {
                Bundle bundle = message.getData();
                ArrayList boardList = (ArrayList)bundle.get("boardList");

                System.out.println("어댑터 호출");
                boardAdapter.boardList=boardList; //어댑터의 List값을 변경
                boardAdapter.notifyDataSetChanged();//어댑터 갱신...
                listView.invalidate();//리스트뷰 갱신
            }
        };

        boardDAO = new BoardDAO(this);
        createSocket();
    }

    //앱이 가동됨과 동시에 웹소켓서버와 접속 시도
    public void createSocket(){
        try {
            myWebSocketClient = new MyWebSocketClient(new URI("ws://192.168.219.100:9999"), this);
            myWebSocketClient.connect();//접속!!
            getList();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //앱이 가동됨과 동시에 웹서버에서 목록 가져오기!!
    // 1.쓰레드+핸들러 ,  2.AsyncTask(Depreacted...)
    public void getList(){
        Thread thread = new Thread(){
            public void run() {
                boardDAO.selectAll();
            }
        };
        thread.start();
    }

    public void getDetail(Board board){
        //상세보기 대화상자 띄우기!!
        //반드시 액티비티를 인수로 넘겨야 함
        detailDialog = new DetailDialog(this);
        detailDialog.show();
        detailDialog.setData(board);
    }

}