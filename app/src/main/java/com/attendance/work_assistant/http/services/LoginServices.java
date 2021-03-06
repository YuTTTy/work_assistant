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
 * Created by aiodiy on 2017/5/22.
 */

public class LoginServices extends BaseService {
    private static final String TAG = LoginServices.class.getSimpleName();
    private Context context;
    private RequestQueue requesQueue;

    public LoginServices(Context context) {
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
     * get session
     *
     * @param username
     * @param password
     * @param handler
     */
    public void getSessionService(String username,String password, final Handler handler) {
        LogUtils.e(TAG, "==============================   get session  request  =======================================");
        String urlStr = MyConstant.BASE_URL + "/system/loginAction!checkuser.action";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
//        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<String>> request = null;
        try {
            request = new GsonRequest<>(context,Request.Method.POST, urlStr,mHeaders,params, new TypeToken<RepBase<String>>() {
            },
                    new Response.Listener<RepBase<String>>() {
                        @Override
                        public void onResponse(RepBase<String> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "get session null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                String responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "get session success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "get session field");
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
     * login
     *
     * @param username
     * @param password
     * @param handler
     */
    public void loginService(String username,String password, final Handler handler) {
        LogUtils.e(TAG, "==============================   login  request  =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!Applogin.action";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<List<Map<String,Object>>>> request = null;
        try {
            request = new GsonRequest<>(context,Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<List<Map<String,Object>>>>() {
            },
                    new Response.Listener<RepBase<List<Map<String,Object>>>>() {
                        @Override
                        public void onResponse(RepBase<List<Map<String,Object>>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "login null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                List<Map<String,Object>> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "login success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "login field");
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
     *             change password
     *
     * @param id
     * @param oldpassword
     * @param newpassword
     * @param handler
     */
    public void changePassService(int id,String oldpassword,String newpassword, final Handler handler) {
        LogUtils.e(TAG, "==============================   change password request   =======================================");
        String urlStr = MyConstant.BASE_URL + "/app/interface!changeSavePassword.action";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("oldpassword", oldpassword);
        params.put("newpassword", newpassword);
        //        getVerificationParams(params, 1);//获取验证参数
        Map<String,String> mHeaders = getHeader();
        GsonRequest<RepBase<Map<String,Object>>> request = null;
        try {
            request = new GsonRequest<>(context,Request.Method.POST, urlStr,mHeaders, params, new TypeToken<RepBase<Map<String,Object>>>() {
            },
                    new Response.Listener<RepBase<Map<String,Object>>>() {
                        @Override
                        public void onResponse(RepBase<Map<String,Object>> response) {
                            if (response == null || response.getSuccess() == null) {
                                Log.e(TAG, "change password null" + response);
                                return;
                            }
                            Message message = new Message();
                            if (response.getSuccess()) {
                                Map<String,Object> responseData = response.getObj();
                                message.what = MyConstant.REQUEST_SUCCESS;
                                message.obj = responseData;
                                Log.e(TAG, "change password success");
                            } else {
                                message.what = MyConstant.REQUEST_FIELD;
                                message.obj = response.getMsg();
                                Log.e(TAG, "change password field");
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
