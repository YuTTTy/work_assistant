package com.attendance.work_assistant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.attendance.work_assistant.extra.TipButton;

/**
 * Created by yujx on 2018/4/18.
 */
public class MainActivity extends AppCompatActivity implements RadioGroup
        .OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private RelativeLayout activity_main;
    private RadioButton radioButton_mine;
    private TipButton tipButton;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}