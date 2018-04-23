package com.attendance.work_assistant.login;


import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.attendance.work_assistant.MainActivity;
import com.attendance.work_assistant.R;

/**
 * Created by yujx on 2018/4/17.
 */
public class LoginActivity extends Activity {
    public static final int CONNECTION_TIMEOUT = 40;
    public static final int READ_TIMEOUT = 40;
    private ImageView avatar_login;
    private EditText etAccount, etPassword;
    private Button button_login;
    private CheckBox rememberpassword_login;
    private SharedPreferences sp;
    private String idValue;
    private String passwordValue;
    private static final int PASSWORD_MIWEN = 0x81;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("UserInfo", Context.MODE_WORLD_READABLE);
        //找到相应的布局
        setContentView(R.layout.activity_login);
        etAccount = findViewById(R.id.account_et);
        etPassword = findViewById(R.id.password_et);
        avatar_login = findViewById(R.id.login_avatar);
        rememberpassword_login = findViewById(R.id.login_rememberpassword);
        button_login = findViewById(R.id.login_button);
        if (sp.getBoolean("ischeck", false)) {
            rememberpassword_login.setChecked(true);
            etAccount.setText(sp.getString("账号", ""));
            etPassword.setText(sp.getString("密码", ""));
            //密文密码
            etPassword.setInputType(PASSWORD_MIWEN);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                etAccount.getPaint().setFlags(0);
                idValue = etAccount.getText().toString();
                etPassword.getPaint().setFlags(0);
                passwordValue = etPassword.getText().toString();

                if (idValue.equals("admin") && passwordValue.equals("123456")) {
                    if (rememberpassword_login.isChecked()) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("账号", idValue);
                        editor.putString("密码", passwordValue);
                        editor.commit();
                    }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账号或者密码错误，请检查后重新登录", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rememberpassword_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rememberpassword_login.isChecked()){
                    System.out.println("记住密码已勾选");
                    sp.edit().putBoolean("ischeck",true).commit();

                }else {
                    System.out.println("记住密码没有勾选");
                    sp.edit().putBoolean("ischeck",false).commit();
                }
            }
        });

    }

}
