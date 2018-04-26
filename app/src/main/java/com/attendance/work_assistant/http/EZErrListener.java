package com.attendance.work_assistant.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.attendance.work_assistant.base.MyConstant;
import com.attendance.work_assistant.http.bean.RepBase;
import com.attendance.work_assistant.http.volley.AuthFailureError;
import com.attendance.work_assistant.http.volley.NetworkError;
import com.attendance.work_assistant.http.volley.NetworkResponse;
import com.attendance.work_assistant.http.volley.NoConnectionError;
import com.attendance.work_assistant.http.volley.ParseError;
import com.attendance.work_assistant.http.volley.ServerError;
import com.attendance.work_assistant.http.volley.TimeoutError;
import com.attendance.work_assistant.http.volley.VolleyError;
import com.attendance.work_assistant.http.volley.toolbox.HttpHeaderParser;
import com.attendance.work_assistant.utils.common.LogUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/14.
 */
public class EZErrListener<T> implements com.attendance.work_assistant.http.volley.Response.ErrorListener, Comparable<EZErrListener<T>> {
    private static String TAG = EZErrListener.class.getSimpleName();
    private Context context;
    private Handler handler;
    private Boolean isBack = true;
    private Gson mGson = new Gson();
    private TypeToken<RepBase<T>> typeToken;

    public EZErrListener(Context context, Boolean isBack) {
        this.context = context;
        this.isBack = isBack;
    }

    public EZErrListener(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public EZErrListener(Context context, Handler handler, TypeToken<RepBase<T>> typeToken) {
        this.context = context;
        this.handler = handler;
        this.typeToken = typeToken;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
//        NetworkResponse networkResponse = volleyError.getNetworkResponse();
        NetworkResponse networkResponse = volleyError.networkResponse;
        Message message = new Message();
        message.what = MyConstant.REQUEST_ERROR;
        String jsonString = null;
        if (networkResponse != null) {
            try {
                jsonString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                LogUtils.e(TAG, "错误信息 request error ------------" + jsonString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            if (volleyError instanceof NetworkError) {
                Log.e(TAG, "网络错误----" + volleyError);
            } else if (volleyError instanceof ServerError) {
                Log.e(TAG, "服务器错误----" + volleyError);
            } else if (volleyError instanceof AuthFailureError) {
                Log.e(TAG, "请检查你的网络----" + volleyError);
            } else if (volleyError instanceof ParseError) {
                Log.e(TAG, "数据解析失败----" + volleyError);
            } else if (volleyError instanceof NoConnectionError) {
                Log.e(TAG, "无网络链接----" + volleyError);
            } else if (volleyError instanceof TimeoutError) {
                Log.e(TAG, "请求超时----" + volleyError);
            }
//            message.obj = "亲，请检查你的网络";
        }
        if (isBack) {
            message.obj = jsonString;
            handler.sendMessage(message);
        }
    }

    @Override
    public int compareTo(EZErrListener<T> another) {
        return 0;
    }
}
