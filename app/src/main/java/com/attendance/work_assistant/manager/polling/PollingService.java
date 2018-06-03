package com.attendance.work_assistant.manager.polling;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.attendance.work_assistant.base.MyConstant;
import com.attendance.work_assistant.http.services.TrailServices;
import com.attendance.work_assistant.utils.common.DateUtil;
import com.attendance.work_assistant.utils.common.LogUtils;
import com.attendance.work_assistant.utils.mine.AppUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by helinjie on 2017/9/28.     轮询服务
 */

public class PollingService extends Service{
    private static final String TAG = PollingService.class.getSimpleName();
    public static final String ACTION = "com.ryantang.service.PollingService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        acquireWakeLock();
    }
    @Override
    public void onStart(Intent intent, int startId) {
//        new PollingThread().start();
        //上传位置数据
        setLocation();

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        releaseWakeLock();
    }

    private PowerManager.WakeLock wakeLock;
    /**
     * 申请电源锁，禁止休眠
     */
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,getClass().getCanonicalName());
            if (null != wakeLock) {
                wakeLock.acquire();
//                wakeLock.setReferenceCounted(false);
                LogUtils.e(TAG,"获取电源锁 -------------------");
            }
        }
    }
    // 释放设备电源锁
    private void releaseWakeLock() {
        if (null != wakeLock) {
            wakeLock.release();
            wakeLock = null;
        }
    }
    private int personId;//    员工id
    private String longitude;//   经度
    private String latitude;//    纬度
    private String label;//    位置文字信息
    private String autoflag = "0";   //上传方式（0：自动，1：手动）
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;
    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void setLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        locationOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        locationOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        locationOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        locationOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        locationOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        locationOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        locationOption.setWifiScan(true);
        //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        locationOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation location) {
                if (null != location) {
                    if (location.getErrorCode() == 0) {
                        longitude = location.getLongitude() + "";
                        latitude = location.getLatitude() + "";
                        label = location.getAddress();
                        LogUtils.e(TAG,"经纬度 ：" + longitude + ", " + latitude + " ; 地址：" + label);
                        requestLoadLoc();
                    } else {
                        LogUtils.e(TAG, "定位失败 ：" + location.getErrorCode());
                    }
                } else {
                    LogUtils.e(TAG, "定位失败，loc is null");
                }
            }
        });
        locationClient.startLocation();
    }

    /**
     * 上传位置
     */
    public void requestLoadLoc() {
        personId = AppUtils.getPersonId(getApplicationContext());
        LogUtils.e(TAG, "上传时间 ：" + DateUtil.getStringByFormat(System.currentTimeMillis(),MyConstant.DATE_FORMAT_YMDHMS));
        new TrailServices(getApplicationContext()).uploadLocService(personId, longitude, latitude,
                label, autoflag, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if (msg.what == MyConstant.REQUEST_SUCCESS) {
                            LogUtils.e(TAG, "上传位置成功");
                        } else if (msg.what == MyConstant.REQUEST_FIELD) {
                            String errMsg = (String) msg.obj;
                            LogUtils.e(TAG, "上传位置失败 ：" + errMsg);
                        } else if (msg.what == MyConstant.REQUEST_ERROR) {
                            String errMsg = (String) msg.obj;
                            LogUtils.e(TAG, "上传位置错误 ：" + errMsg);
                        }
                    }
                });
    }

    /**
     *    start location
     */
    public void startLocation() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                locationClient.startLocation();
            }
        },3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocation();
        LogUtils.e(TAG,"Service:onDestroy");
    }
    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        if (null != locationClient) {
            LogUtils.e(TAG,"停止定位");
            // 停止定位
            locationClient.stopLocation();
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
}
