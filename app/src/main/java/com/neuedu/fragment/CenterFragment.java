package com.neuedu.fragment;

import android.content.Intent;
import android.media.Image;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.neuedu.HomeActivity;
import com.neuedu.activity_changeUserMessage;
import com.neuedu.businessproject.R;
import com.neuedu.user_login;
import com.neuedu.utils.SharedPreferencesUtil;
import com.neuedu.vo.UserVO;

public class CenterFragment extends Fragment implements View.OnClickListener {

    ImageView user_photo;
    Button userInfo;
    Button logout;
    TextView info;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.center_fragment,container,false);

        user_photo = view.findViewById(R.id.user_photo);
        userInfo = view.findViewById(R.id.userInfo);
        info = view.findViewById(R.id.info);
        userInfo.setOnClickListener(this);
        user_photo.setOnClickListener(this);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //判断用户是否登录
        Boolean isLogin= SharedPreferencesUtil.getInstance(getActivity()).readBoolean("isLogin");
        if(isLogin){
            //已经登录
            //获取用户信息
            UserVO userVO=(UserVO) SharedPreferencesUtil.getInstance(getActivity()).readObject("user", UserVO.class);
            info.setText(userVO.getUsername());
        }else {
            info.setText("请先登录");
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), user_login.class);
                    CenterFragment.this.startActivity(intent);
                }
            });
            user_photo.setImageResource(R.mipmap.user_unselected);

            user_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), user_login.class);
                    CenterFragment.this.startActivity(intent);
                }
            });
        }

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_photo:
                //点击了图片之后的响应
                /*Intent intent = new Intent(getActivity(),activity_changeUserMessage.class);
                CenterFragment.this.startActivity(intent);
                break;*/
            case R.id.userInfo:
                Intent intent = new Intent(getActivity(),activity_changeUserMessage.class);
                CenterFragment.this.startActivity(intent);
                break;
            case R.id.logout:
                //登出按钮
                SharedPreferencesUtil util= SharedPreferencesUtil.getInstance(getActivity());
                util.delete("isLogin");
                util.delete("user");
//                Intent intent1 = new Intent(getActivity(),user_login.class);
                Intent intent1 = new Intent(getActivity(),HomeActivity.class);
                CenterFragment.this.startActivity(intent1);
                break;
        }
    }
}
