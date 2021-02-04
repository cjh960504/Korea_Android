package com.study.graphicapp.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.URL;

//URL이미지를 보여줄 뷰
public class GalleryView extends View {
    Bitmap bitmap;
    public GalleryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void loadImage(){
        URL url = new URL("");
        try {
            BitmapFactory.decodeStream(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawBitmap(0, 0, bitmap, null);
    }
}
