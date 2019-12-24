package com.neuedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neuedu.businessproject.R;

public class activity_changeUserMessage extends AppCompatActivity implements View.OnClickListener {

    Button btn_userMessageChange;
    Button btn_edit;

    EditText edit_username;
    EditText edit_phone;
    EditText edit_email;
    EditText edit_regtime;
    EditText edit_ip;

    TextView text_email;
    TextView text_phone;
    TextView text_ip;
    TextView text_regtime;
    TextView text_username;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_message);

        btn_userMessageChange = (Button) findViewById(R.id.btn_changeUserMessage);
        btn_edit = (Button) findViewById(R.id.btn_edit);
        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_regtime = (EditText) findViewById(R.id.edit_regtime);
        edit_ip = (EditText) findViewById(R.id.edit_ip);
        text_email = (TextView) findViewById(R.id.text_email);
        text_phone = (TextView) findViewById(R.id.text_phone);
        text_ip = (TextView) findViewById(R.id.text_ip);
        text_regtime = (TextView) findViewById(R.id.text_regtime);
        text_username = (TextView) findViewById(R.id.text_username);

        btn_edit.setOnClickListener(this);
        btn_userMessageChange.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_changeUserMessage:
                Toast.makeText(activity_changeUserMessage.this,"点击了修改按钮",Toast.LENGTH_LONG).show();

                //对修改的信息进行获取
                String username = edit_username.getText().toString();
                String email = edit_email.getText().toString();
                String phone = edit_phone.getText().toString();
                String ip = edit_ip.getText().toString();
                String regtime = edit_regtime.getText().toString();

                Toast.makeText(activity_changeUserMessage.this,username+" "+email+" "+phone+" "+ip+" "+regtime,Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_edit:
                text_username.setVisibility(View.INVISIBLE);
                edit_username.setVisibility(View.VISIBLE);
                text_email.setVisibility(View.INVISIBLE);
                edit_email.setVisibility(View.VISIBLE);
                text_phone.setVisibility(View.INVISIBLE);
                edit_phone.setVisibility(View.VISIBLE);
                text_ip.setVisibility(View.INVISIBLE);
                edit_ip.setVisibility(View.VISIBLE);
                text_regtime.setVisibility(View.INVISIBLE);
                edit_regtime.setVisibility(View.VISIBLE);
                btn_userMessageChange.setVisibility(View.VISIBLE);
                break;
        }
    }
}
