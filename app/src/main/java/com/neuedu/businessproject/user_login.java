package com.neuedu.businessproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class user_login extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_username);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //如果是登录按钮
                String name = username.getText().toString();
                String pass = password.getText().toString();
                break;
        }
    }
}