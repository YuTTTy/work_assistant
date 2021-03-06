package com.attendance.work_assistant.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attendance.work_assistant.R;

import static com.attendance.work_assistant.base.BaseActivity.loginOut;
import static com.attendance.work_assistant.base.BaseActivity.setSwipRefresh;

/**
 * Created by helinjie on 2017/9/2.
 */

public class NoticeFragment extends Fragment {
    private static final String TAG = NoticeFragment.class.getSimpleName();
    private SwipeRefreshLayout swipeLayout;
    private Activity activity;

    private TextView login_out_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    public void initView() {
        swipeLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh_layout);
        swipeLayout.setEnabled(false); //禁止下拉刷新
        setSwipRefresh(swipeLayout, null);
        login_out_text = (TextView) getActivity().findViewById(R.id.login_out_text);
        login_out_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginOut(getActivity());
            }
        });
    }

    public void initData() {

    }
}
