package com.attendance.work_assistant.ui.checkwork.clockrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.attendance.work_assistant.R;
import com.attendance.work_assistant.base.BaseActivity;

import java.util.Map;

/**
 * Created by helinjie on 2017/9/3.     打卡详情
 */

public class ClockDetailActivity extends BaseActivity {
    private static final String TAG = ClockDetailActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeLayout;
    private Intent intent;

    private LinearLayout items_layout;
    private Map<String, Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentLayout(R.layout.activity_clock_detail);
    }

    @Override
    public void setTitleAttribute() {
        setTitle(0, R.color.titleBg, R.drawable.ico_left_gray, "返回", R.color.baseTextMain, "打卡记录", R.color.baseTextMain, "", R.color.baseTextMain, 0);
    }

    @Override
    public void initView() {
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeLayout.setEnabled(false); //禁止下拉刷新
        setSwipRefresh(swipeLayout, null);
        items_layout = (LinearLayout) findViewById(R.id.clock_detail_items_layout);

    }

    @Override
    public void initData() {
        intent = getIntent();
        data = (Map<String, Object>) intent.getSerializableExtra("clockDetail");
        if (data != null) {
            String address = "";
            if (data.get("label") != null) {
                address = (String) data.get("label");
            }
            if (data.get("createTime") != null) {
                String createTime = (String) data.get("createTime");
                if (data.get("signflag")!= null){
                    int signFlag = new Double((Double)data.get("signflag")).intValue();
                    if (signFlag == 0){
                        items_layout.addView(addItemView("签到", address, createTime));
                    }else if (signFlag == 1){
                        items_layout.addView(addItemView("签退", address, createTime));
                    }
                }
            }

            if (data.get("createdate") != null) {
                String createData = (String) data.get("createdate");

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public View addItemView(String nameStr, String addressStr, String timeStr) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_clock_detail, null);
        TextView name_text = (TextView) itemView.findViewById(R.id.item_clock_detail_text);
        TextView address_text = (TextView) itemView.findViewById(R.id.item_clock_detail_address);
        TextView time_text = (TextView) itemView.findViewById(R.id.item_clock_detail_time);
        name_text.setText(nameStr);
        if (addressStr != null && !addressStr.equals("")){
            address_text.setText(addressStr);
        }
        time_text.setText(timeStr);
        return itemView;
    }
}
