package com.attendance.work_assistant.http.services;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.attendance.work_assistant.base.MyConstant;
import com.attendance.work_assistant.http.EZErrListener;
import com.attendance.work_assistant.http.GsonRequest;
import com.attendance.work_assistant.http.bean.RepBase;
import com.attendance.work_assistant.http.volley.Request;
import com.attendance.work_assistant.http.volley.RequestQueue;
import com.attendance.work_assistant.http.volley.Response;
import com.attendance.work_assistant.http.volley.toolbox.HttpStack;
import com.attendance.work_assistant.http.volley.toolbox.OwnHttpClientStack;
import com.attendance.work_assistant.http.volley.toolbox.Volley;
import com.attendance.work_assistant.utils.common.LogUtils;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by helinjie on 2017/9/15.
 */

public class TrailServices extends BaseService {
    private static final String TAG = TrailServices.class.getSimpleName();
    private Context context;
    private RequestQueue requesQueue;

    public TrailServices(Context context) {
        super(context);
        this.context = context;
        //        queue = Volley.newRequestQueue(context);
        String userAgent = "volley/0";
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        HttpStack httpStack = new OwnHttpClientStack(AndroidHttpClient.newInstance(userAgent));
        requesQueue = Volley.newRequestQueue(context, httpStack);
    }

    /**
     *                      用户上传位置
     *
     * @param personId    员工id
     * @param longitude   经度
     * @param latitude    纬度
     * @param label    位置文字信息
     * @param autoflag   上传方式（0：自动，1：手动）
     * @param handler
     */
    public void uploadLocService(int personId,String longitude,String latitude, String label,
                                 String autoflag,final Handler handler) {
        LogUtils.e(TAG, "==============================   用户上传位置 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/locationInfo!uploadLocIn.action";
        Map<String, Object> params = new HashMap<>();
        params.put("personId", personId);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("label", label);
        params.put("autoflag", autoflag);
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<Map<String,Object>>> request = null;
        try {
            request = new GsonRequest<>(context, Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<Map<String,Object>>>() {
            },
                    new Response.Listener<RepBase<Map<String,Object>>>() {
                        @Override
                        public void onResponse(RepBase<Map<String,Object>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "用户上传位置 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "用户上传位置 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "用户上传位置 field");
                            }
                            handler.sendMessage(message);
                        }
                    }, new EZErrListener<>(context, handler));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requesQueue.add(request);
    }

    /**
     *                      获取用户最新位置
     *
     * @param personId   员工id
     * @param handler
     */
    public void queryUserLocService(int personId,final Handler handler) {
        LogUtils.e(TAG, "==============================   获取用户最新位置 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/locationInfo!queryUserLoc.action";
        Map<String, Object> params = new HashMap<>();
        params.put("personId", personId);
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<Map<String,Object>>> request = null;
        try {
            request = new GsonRequest<>(context, Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<Map<String,Object>>>() {
            },
                    new Response.Listener<RepBase<Map<String,Object>>>() {
                        @Override
                        public void onResponse(RepBase<Map<String,Object>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "获取用户最新位置 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "获取用户最新位置 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "获取用户最新位置 field");
                            }
                            handler.sendMessage(message);
                        }
                    }, new EZErrListener<>(context, handler));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requesQueue.add(request);
    }

    /**
     *                      获取用户当天轨迹图
     *
     * @param personId   员工id
     * @param handler
     */
    public void queryUserRouteService(int personId,final Handler handler) {
        LogUtils.e(TAG, "==============================   获取用户当天轨迹图 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/locationInfo!queryUserRoute.action";
        Map<String, Object> params = new HashMap<>();
        params.put("personId", personId);
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<Map<String,Object>>> request = null;
        try {
            request = new GsonRequest<>(context, Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<Map<String,Object>>>() {
            },
                    new Response.Listener<RepBase<Map<String,Object>>>() {
                        @Override
                        public void onResponse(RepBase<Map<String,Object>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "获取用户当天轨迹图 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "获取用户当天轨迹图 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "获取用户当天轨迹图 field");
                            }
                            handler.sendMessage(message);
                        }
                    }, new EZErrListener<>(context, handler));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requesQueue.add(request);
    }
    /**
     *                    部门列表
     *
     * @param handler
     */
    public void departmentListService(final Handler handler) {
        LogUtils.e(TAG, "==============================   部门列表 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!departmentList.action";
        Map<String, Object> params = new HashMap<>();
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<List<Map<String,Object>>>> request = null;
        try {
            request = new GsonRequest<>(context, Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<List<Map<String,Object>>>>() {
            },
                    new Response.Listener<RepBase<List<Map<String,Object>>>>() {
                        @Override
                        public void onResponse(RepBase<List<Map<String,Object>>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "部门列表 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                List<Map<String,Object>> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "部门列表 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "部门列表 field");
                            }
                            handler.sendMessage(message);
                        }
                    }, new EZErrListener<>(context, handler));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requesQueue.add(request);
    }
    /**
     *                    员工列表
     *
     * @param departmentid 部门id
     * @param handler
     */
    public void employeeListService(int departmentid,final Handler handler) {
        LogUtils.e(TAG, "==============================   员工列表 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!employeeList.action";
        Map<String, Object> params = new HashMap<>();
        params.put("departmentId",departmentid);
//        params.put("dpmname",dpmname);
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<List<Map<String,Object>>>> request = null;
        try {
            request = new GsonRequest<>(context, Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<List<Map<String,Object>>>>() {
            },
                    new Response.Listener<RepBase<List<Map<String,Object>>>>() {
                        @Override
                        public void onResponse(RepBase<List<Map<String,Object>>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "员工列表 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                List<Map<String,Object>> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "员工列表 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "员工列表 field");
                            }
                            handler.sendMessage(message);
                        }
                    }, new EZErrListener<>(context, handler));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requesQueue.add(request);
    }
}
