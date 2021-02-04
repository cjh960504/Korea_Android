package com.study.graphicapp.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.study.graphicapp.R;

public class GalleryActivity extends AppCompatActivity {
    GalleryView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        galleryView = findViewById(R.id.galleryView);
    }
}