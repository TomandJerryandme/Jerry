package com.neuedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neuedu.businessproject.R;
import com.neuedu.utils.OkHttpCallback;
import com.neuedu.utils.OkHttpUtils;
import com.neuedu.utils.SharedPreferencesUtil;
import com.neuedu.vo.ServerResponse;
import com.neuedu.vo.UserVO;

public class user_login extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //如果是登录按钮
                String name = username.getText().toString();
                String pass = password.getText().toString();

                //Toast.makeText(user_login.this,name+" "+pass,Toast.LENGTH_LONG).show();
                /*Looper.prepare();
                Toast.makeText(user_login.this,name,Toast.LENGTH_LONG).show();
                Looper.loop();*/

                OkHttpUtils.get("http://10.25.132.94:8080/pro/user/login?username="+name+"&password="+pass,new OkHttpCallback(){
                    @Override
                    public void onFinish(String status, String msg) {
                        super.onFinish(status, msg);

                        //解析数据
                        Gson gson = new Gson();
                        ServerResponse<UserVO> serverResponse=gson.fromJson(msg, new TypeToken<ServerResponse<UserVO>>(){}.getType());

                        int loginStatus = serverResponse.getStatus();
                        if (loginStatus==0){
                            SharedPreferencesUtil util= SharedPreferencesUtil.getInstance(user_login.this);
                            util.delete("isLogin");
                            util.delete("user");
                            util.putBoolean("isLogin",true);
                            util.putString("user",gson.toJson(serverResponse.getData()));
                            Boolean isLogin= util.readBoolean("isLogin");

                            //Activity跳转
                            Intent intent=new Intent(user_login.this,HomeActivity.class);
                            user_login.this.startActivity(intent);

                            //登录成功
                            Looper.prepare();
                            Toast.makeText(user_login.this,"登录成功",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }else{
                            Looper.prepare();
                            Toast.makeText(user_login.this,"登录失败",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }
                });
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}