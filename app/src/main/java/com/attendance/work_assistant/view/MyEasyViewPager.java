package com.attendance.work_assistant.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yujx on 2018/4/16.
 */
public class MyEasyViewPager extends ViewPager {
    public MyEasyViewPager(Context paramContext,AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }
    protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
    {
        if ((paramView != this) && ((paramView instanceof ViewPager))) {
            return true;
        }
        return super.canScroll(paramView, paramBoolean, paramInt1, paramInt2, paramInt3);
    }
}