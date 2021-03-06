package com.study.actionbarapp.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.study.actionbarapp.MainActivity;
import com.study.actionbarapp.R;

import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {
    //리소스에 있는 자원이용 ...... X 
    //원격지에 있는 URL로 떙겨와야함 - 네트워크 http 요청 --> 웹 서버 구축..
    String TAG=this.getClass().getName();
    MainActivity mainActivity;
    LayoutInflater layoutInflater;
    ArrayList<Gallery> galleryList = new ArrayList<Gallery>();

    public GalleryAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        layoutInflater=mainActivity.getLayoutInflater(); //액티비티를 통해 인플레이터 얻기
    }

    @Override
    public int getCount() {
        return galleryList.size(); //임시적으로...
    }

    @Override
    public Object getItem(int position) {
        return galleryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null; //이 메서드에서 최종 반환할 뷰

        if(convertView == null){//레이아웃 뷰가 존재하지 않는다면.. 인플레이션 시킴
            //false의 의미 지정한 parent에 부착하지 않고, 인플레이션 대상  xml의 최상위를 반환
            view = layoutInflater.inflate(R.layout.item_gallery, parent, false);
        }else{//이미 존재한다면, 기존 뷰 그래도 재사용함
            view=convertView;
        }

        //리스트에 들어있는 position 번째 Gallery 추출
        Gallery gallery  =galleryList.get(position);
        ImageView img = view.findViewById(R.id.img);
        img.setImageBitmap(gallery.getBitmap());

        return view;
    }

}
