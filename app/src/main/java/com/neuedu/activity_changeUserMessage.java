package com.neuedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neuedu.businessproject.R;
import com.neuedu.utils.OkHttpCallback;
import com.neuedu.utils.OkHttpUtils;
import com.neuedu.utils.SharedPreferencesUtil;
import com.neuedu.vo.ServerResponse;
import com.neuedu.vo.UserVO;

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


        UserVO userVO=(UserVO) SharedPreferencesUtil.getInstance(this).readObject("user", UserVO.class);

        //对用户信息界面的TextView设置初始值
        text_ip.setText(userVO.getIp());
        text_phone.setText(userVO.getPhone());
        text_email.setText(userVO.getEmail());
        text_username.setText(userVO.getUsername());
        text_regtime.setText(userVO.getCreateTime());
        edit_username.setText(userVO.getUsername());
        edit_regtime.setText(userVO.getCreateTime());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_changeUserMessage:
                //Toast.makeText(activity_changeUserMessage.this,"点击了修改按钮",Toast.LENGTH_LONG).show();

                //对修改的信息进行获取
                String username = edit_username.getText().toString();
                String email = edit_email.getText().toString();
                String phone = edit_phone.getText().toString();
                String ip = edit_ip.getText().toString();
                String regtime = edit_regtime.getText().toString();

                OkHttpUtils.get("http://10.25.132.94:8080/pro/user/change?username="+username+"&email="+email+"&phone="+phone+"&ip="+ip+"&create_time="+regtime,new OkHttpCallback(){
                    @Override
                    public void onFinish(String status, String msg) {
                        super.onFinish(status, msg);

                        Gson gson = new Gson();
                        ServerResponse<UserVO> serverResponse=gson.fromJson(msg, new TypeToken<ServerResponse<UserVO>>(){}.getType());

                        if (serverResponse.getStatus()==0){
                            //修改信息成功
                            Toast.makeText(activity_changeUserMessage.this,"修改用户信息成功",Toast.LENGTH_LONG).show();
                        }
                    }
                });


                //Toast.makeText(activity_changeUserMessage.this,username+" "+email+" "+phone+" "+ip+" "+regtime,Toast.LENGTH_LONG).show();

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
