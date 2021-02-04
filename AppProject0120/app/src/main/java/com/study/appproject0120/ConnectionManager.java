/*
자바도 html문서처럼, 웹서버와 http통신이 가능하다..
*/
package com.study.appproject0120;

import android.util.Log;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

public class  ConnectionManager extends Thread{
    //http통신을 위한 객체
    //(헤더+바디를 구성하여 서버와 데이터를 주고받는 방식)
    //stateless 한 통신
    URL url;
    HttpURLConnection con;
    String requestURL;
    String data;
    String TAG = this.getClass().getName();

    //이 객체를 생성 시 주소와 JSON데이터를 넘겨야한다.
    public ConnectionManager(String requestURL, String data){
        this.requestURL = requestURL;
        this.data = data;
    }

    public void requestByGet(){
        BufferedReader buffr = null;
        try{
            url = new URL("http://192.168.219.100:8888/rest/member");//요청 주소
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            //서버로부터 응답 데이터 가져오기
            //버퍼/리더/스트림
            //한줄/한글처리/바이트단위
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream())); //바이트 기반 스트림
            StringBuilder sb = new StringBuilder();//문자열을 누적할 객체
            String data = null;
            while(true){
                data=buffr.readLine();//한줄읽어들이기
                if(data==null) break;//읽어들일 데이터가 없다면 종료
                sb.append(data);
            }
            int code = con.getResponseCode();//서버로부터 받은 응답코드반환
            //(이 시점에 이미 서버에 요청을 완료 후, 응답도 받은상태)
            System.out.println("서버로부터 받은 응답코드 : "+code);
            System.out.println("서버로부터 받은 응답데이터 : "+sb.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(buffr!=null){
                try{
                    buffr.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    //Post방식의 요청을 시도하되, JSON데이터를 전송
    public int requestByPost(){
        BufferedWriter buffw = null;
        int code=0;//서버의 응답 코드

        try{
            url = new URL(requestURL);//요청 주소
            con = (HttpURLConnection)url.openConnection();
            //데이터 형식을 헤더에 첨가해줘야, 서버측에서 JSON데이터가 전송되어 온것임을 안다..
            //이게바로 HTTP프로토콜간의 약속이다. 즉, 헤더가 통신 시의 필요한 정보를 가짐
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.setRequestMethod("POST");
            con.setDoOutput(true);//서버에 데이터를 출력하기 위해 필요한 옵션!
            //요청을 떠나기 전에, 준비할게 있다면 여기서 준비!(Body)
            //JSON 객체가 아닌 문자열로 준비하는 이유는?
            //통신상에서는 데이터를 쪼개서 보내야하기 떄문에 객체 그 자체로는
            //전송이 불가능하다.
            StringBuilder sb = new StringBuilder();
			/*sb.append("{");
			sb.append("\"m_id\":\"batman\",");
			sb.append("\"m_pass\":\"1234\",");
			sb.append("\"m_name\":\"배트맨\"");
			sb.append("}");*/

            //실행중인 프로그램에서 서버로 데이터를 보내야하므로, 출력스트림으로 처리하자!!
            buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8));
            buffw.write(data);
            buffw.flush();

            code = con.getResponseCode();//요청+응답
            System.out.println("서버로부터 받은 응답코드 : "+code);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(buffw!=null){
                try{
                    buffw.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return code;
    }

    @Override
    public void run() {
        int code = requestByPost();
        Log.d(TAG, "서버로부터 받은 응답코드 : "+code);
    }
}
