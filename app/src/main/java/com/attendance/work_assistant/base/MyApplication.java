package com.attendance.work_assistant.base;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private Context applicationContext;

    public static MyApplication myApplication;

    private static Object obj = new Object();
    private static MyApplication instance = null;

    public static MyApplication getInstance() {
        // if already inited, no need to get lock everytime
//        if (instance == null) {
//            synchronized (obj) {
        if (instance == null) {
            instance = new MyApplication();
        }
//            }
//        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        myApplication = this;
        applicationContext = this;
    }

    /*是否首次进入主页*/
    private static Boolean firstMain = true;
    private static Map<String,Object> loginData;
    private static String session; //

    public void clearMemory(){

    }

    public static Map<String, Object> getLoginData() {
        return loginData;
    }

    public static void setLoginData(Map<String, Object> loginData) {
        MyApplication.loginData = loginData;
    }

    public static Boolean getFirstMain() {
        return firstMain;
    }

    public static void setFirstMain(Boolean firstMain) {
        MyApplication.firstMain = firstMain;
    }

    public static String getSession() {
        return session;
    }

    public static void setSession(String session) {
        MyApplication.session = session;
    }
}
