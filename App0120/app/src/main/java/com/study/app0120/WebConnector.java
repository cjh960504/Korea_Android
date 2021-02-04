package com.study.app0120;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*스프링 웹 서버와 통신하기 위한 객체*/
public class WebConnector {
    URL url;
    HttpURLConnection con;

    public void getData(){
        try {
            url = new URL("http:/192.168.219.100:8888/rest/member");
            con = (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
