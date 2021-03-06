package com.study.actionbarapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.study.actionbarapp.chat.ChatFragment;
import com.study.actionbarapp.gallery.GalleryFragment;
import com.study.actionbarapp.mp3.MusicFragment;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments = new Fragment[3];

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragments[0] = new MusicFragment(); // 페이지 생성
        fragments[1] = new ChatFragment();
        fragments[2] = new GalleryFragment();
    }

    //몇 페이지?
    public int getCount() {
        return fragments.length;
    }

    //각 포지션에 어떤 페이지를?
    public Fragment getItem(int position) {
        return fragments[position];
    }
}
