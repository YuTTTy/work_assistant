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
import java.util.Map;

/**
 * Created by helinjie on 2017/9/26.  我的申请、审批
 */

public class CheckServices extends BaseService {
    private static final String TAG = CheckServices.class.getSimpleName();
    private Context context;
    private RequestQueue requesQueue;

    public CheckServices(Context context) {
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
     *                    我的申请列表
     *
     * @param personid   申请人id
     * @param page   当前页
     * @param rows   每页显示记录数
     * @param handler
     */
    public void myApplyService(int personid,int page,int rows,final Handler handler) {
        LogUtils.e(TAG, "==============================   我的申请列表 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!applications.action";
        Map<String, Object> params = new HashMap<>();
        params.put("personid", personid);
        params.put("page", page);
        params.put("rows", rows);
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
                                Log.e(TAG, "我的申请列表 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "我的申请列表 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "我的申请列表 field");
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
     *                    我的待审批列表
     *
     * @param approvalId   申请人id
     * @param page   当前页
     * @param rows   每页显示记录数
     * @param handler
     */
    public void examineService(int approvalId,int page,int rows,final Handler handler) {
        LogUtils.e(TAG, "==============================   我的待审批列表 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!approvaling.action";
        Map<String, Object> params = new HashMap<>();
        params.put("approvalId", approvalId);
        params.put("page", page);
        params.put("rows", rows);
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
                                Log.e(TAG, "我的待审批列表 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "我的待审批列表 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "我的待审批列表 field");
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
     *                    我的已审批列表
     *
     * @param approvalId   申请人id
     * @param page   当前页
     * @param rows   每页显示记录数
     * @param handler
     */
    public void alExamineService(int approvalId,int page,int rows,final Handler handler) {
        LogUtils.e(TAG, "==============================   我的已审批列表 request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!approvaled.action";
        Map<String, Object> params = new HashMap<>();
        params.put("approvalId", approvalId);
        params.put("page", page);
        params.put("rows", rows);
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
                                Log.e(TAG, "我的已审批列表 null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = (Map<String, Object>) response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "我的已审批列表 success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "我的已审批列表 field");
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
