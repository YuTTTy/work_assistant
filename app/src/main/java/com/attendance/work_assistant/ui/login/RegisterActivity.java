package com.attendance.work_assistant.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.attendance.work_assistant.R;
import com.attendance.work_assistant.dao.UserDataManager;
import com.attendance.work_assistant.model.UserData;
import com.attendance.work_assistant.utils.common.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.resetpwd_edit_name)
    EditText setUserName;
    @BindView(R.id.resetpwd_edit_pwd_new)
    EditText setUserPwd;
    @BindView(R.id.resetpwd_edit_pwd_old)
    EditText confirmUserPwd;
    @BindView(R.id.register_btn_sure)
    Button btn_sure;
    @BindView(R.id.register_btn_cancel)
    Button btn_cancle;
    private UserDataManager mUserDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
    }
    @OnClick({R.id.register_btn_sure,R.id.register_btn_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn_sure:                       //确认按钮的监听事件
                register_check();
                break;
            case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class);
                // Activity至Login Activity
                startActivity(intent_Register_to_Login);
                finish();
                break;
        }
    }


    public void register_check() {                                //确认按钮的监听事件
        if (isUserNameAndPwdValid()) {
            String userName = setUserName.getText().toString().trim();
            String userPwd = setUserPwd.getText().toString().trim();
            String userPwdCheck = confirmUserPwd.getText().toString().trim();
            //检查用户是否存在
            int count = mUserDataManager.findUserByName(userName);
            //用户已经存在时返回，给出提示文字
            if(count>0){
                Toast.makeText(this, "用户名已存在！！！",Toast.LENGTH_SHORT).show();
                return ;
            }
            if(userPwd.equals(userPwdCheck)==false){     //两次密码输入不一样
                Toast.makeText(this, getString(R.string.pwd_not_the_same),Toast.LENGTH_SHORT).show();
                return ;
            } else {
                UserData mUser = new UserData(userName, userPwd);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUserData(mUser); //新建用户信息
                if (flag == -1) {
                    Toast.makeText(this, "注册失败！！！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "注册成功！！！",Toast.LENGTH_SHORT).show();
                    Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity
                            .class) ;
                    //切换Register Activity至Login Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (setUserName.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (setUserPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }else if(confirmUserPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码为空！！！",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    long exitTime = 0L; //退出时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtils.showShort(RegisterActivity.this, "再按一次返回退出程序");
                exitTime = System.currentTimeMillis();
            } else {
//                android.os.Process.killProcess(Process.myPid());
                finish();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
