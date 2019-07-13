package com.example.chapter3.homework;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.airbnb.lottie.LottieAnimationView;

/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */
public class Ch3Ex3Activity extends AppCompatActivity {

    private final static int showTime =  1000;
    private final static String[] title = {"消息列表", "好友列表"};
    private final static String TAG = "Ch3Ex3";
    private LottieAnimationView lottie = null;
    private ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);


        viewPager = findViewById(R.id.view_pager);
        lottie = findViewById(R.id.lottie_animation_view);
        viewPager.setAlpha(0.0f);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator lottieAlpha = ObjectAnimator.ofFloat(lottie,
                        "alpha", 1.0f, 0.0f);
                lottieAlpha.setRepeatCount(0);
                lottieAlpha.setInterpolator(new LinearInterpolator());
                lottieAlpha.setDuration(showTime);
                lottieAlpha.setRepeatMode(ObjectAnimator.REVERSE);

                ObjectAnimator viewPagerAlpha = ObjectAnimator.ofFloat(viewPager,
                        "alpha", 0.0f, 1.0f);
                viewPagerAlpha.setRepeatCount(0);
                viewPagerAlpha.setInterpolator(new LinearInterpolator());
                viewPagerAlpha.setDuration(showTime);
                viewPagerAlpha.setRepeatMode(ObjectAnimator.REVERSE);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(lottieAlpha, viewPagerAlpha);
                animatorSet.start();
            }
        }, 5000);


        // TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面
        // TODO: ex3-2, 添加 TabLayout 支持 Tab
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0){
                    return new NewsFragment();
                }
                else{
                    return new FriendsFragment();
                }
            }

            @Override
            public int getCount() {
                return title.length;
            }

            @Override
            public void startUpdate(@NonNull ViewGroup container) {
                super.startUpdate(container);
                Log.d(TAG, "startUpdate() called with: container = [" + container + "]");
                ShowLottie();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }

        });
        tabLayout.setupWithViewPager(viewPager);

    }
    private void ShowLottie(){
        Log.d(TAG, "ShowLottie: 1");
        if(lottie.getAlpha() == 0.0f) {
            Log.d(TAG, "ShowLottie: 2");

            ObjectAnimator lottieAlpha = ObjectAnimator.ofFloat(lottie,
                    "alpha", 1.0f, 0.0f);
            lottieAlpha.setRepeatCount(0);
            lottieAlpha.setInterpolator(new LinearInterpolator());
            lottieAlpha.setDuration(showTime);
            lottieAlpha.setRepeatMode(ObjectAnimator.REVERSE);

            ObjectAnimator viewPagerAlpha = ObjectAnimator.ofFloat(viewPager,
                    "alpha", 0.4f, 1.0f);
            viewPagerAlpha.setRepeatCount(0);
            viewPagerAlpha.setInterpolator(new LinearInterpolator());
            viewPagerAlpha.setDuration(showTime);
            viewPagerAlpha.setRepeatMode(ObjectAnimator.REVERSE);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(lottieAlpha, viewPagerAlpha);
            animatorSet.start();
        }
    }
}
