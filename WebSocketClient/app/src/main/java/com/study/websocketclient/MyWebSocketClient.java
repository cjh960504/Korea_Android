package com.study.websocketclient;

import android.util.Log;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {
    String TAG = this.getClass().getName();
    Gson gson = new Gson();
    MainActivity mainActivity;
    public MyWebSocketClient(URI serverUri, MainActivity mainActivity) {
        super(serverUri);
        this.mainActivity = mainActivity;
    }

    public MyWebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    //서버와 연결되면..
    public void onOpen(ServerHandshake handshakedata) {
        Log.d(TAG, "onOpen called");

    }

    //메세지가 도착하면..
    public void onMessage(String message) {
        Log.d(TAG, "onMessage called" + message);

        try {
            JSONObject json = new JSONObject(message);
            if(json.get("requestCode").equals("create")){
                mainActivity.boardDAO.selectAll();
                Log.d(TAG,"글쓰기 발생");
            }else if(json.get("requestCode").equals("update")){
                mainActivity.boardDAO.selectAll();
                Log.d(TAG,"수정 발생");
            }else if(json.get("requestCode").equals("delete")){
                mainActivity.boardDAO.selectAll();
                Log.d(TAG,"삭제 발생");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //메시지 보내기
    public void sendMsg(SocketMessage socketMessage){
        String jsonString = gson.toJson(socketMessage);
        this.send(jsonString);
    }

    //접속이 끊기면..
    public void onClose(int code, String reason, boolean remote) {
        Log.d(TAG, "onClose called");
    }

    //에러가 발생하면..
    public void onError(Exception ex) {
        Log.d(TAG, "onError called");
    }
}
