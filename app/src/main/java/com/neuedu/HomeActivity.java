package com.neuedu;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.neuedu.businessproject.R;
import com.neuedu.fragment.CartFragment;
import com.neuedu.fragment.CenterFragment;
import com.neuedu.fragment.HomeFragment;
import com.neuedu.fragment.OrderFragment;
import com.neuedu.fragment.UserCenterFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout host_LinearLayout;
    LinearLayout order_LinearLayout;
    LinearLayout cart_LinearLayout;
    LinearLayout center_LinearLayout;

    Button userInfo;

    public static final String HOMEFRAGMENT_TAG = "HOME";
    public static final String CARTFRAGMENT_TAG = "CART";
    public static final String ORDERFRAGMENT_TAG = "ORDER";
    public static final String CENTERFRAGMENT_TAG = "CENTER";

    ImageView image_center;
    ImageView image_home;
    ImageView image_order;
    ImageView image_cart;

    ImageView user_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        host_LinearLayout = (LinearLayout) findViewById(R.id.home_LinearLayout);
        order_LinearLayout = (LinearLayout) findViewById(R.id.order_LinearLayout);
        cart_LinearLayout = (LinearLayout) findViewById(R.id.cart_LinearLayout);
        center_LinearLayout = (LinearLayout) findViewById(R.id.center_LinearLayout);


        host_LinearLayout.setOnClickListener(this);
        order_LinearLayout.setOnClickListener(this);
        center_LinearLayout.setOnClickListener(this);
        cart_LinearLayout.setOnClickListener(this);

        //无法在这里为该图片进行点击事件的注册


        image_cart = (ImageView) findViewById(R.id.image_cart);
        image_center = (ImageView) findViewById(R.id.image_center);
        image_home = (ImageView) findViewById(R.id.image_home);
        image_order = (ImageView) findViewById(R.id.image_order);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_LinearLayout:
                attachFragment(HOMEFRAGMENT_TAG);
                break;
            case R.id.cart_LinearLayout:
                attachFragment(CARTFRAGMENT_TAG);
                break;
            case R.id.order_LinearLayout:
                attachFragment(ORDERFRAGMENT_TAG);
                break;
            case R.id.center_LinearLayout:
                attachFragment(CENTERFRAGMENT_TAG);
                break;
            case R.id.userInfo:
                //如果是点击用户信息的按钮
                Intent intent = new Intent(HomeActivity.this,activity_changeUserMessage.class);
                HomeActivity.this.startActivity(intent);
                break;
            case R.id.user_photo:
                //如果点击了用户头像，则跳转到用户信息界面
                //Intent intent = new Intent(HomeActivity.this,activity_changeUserMessage.class);
                break;

        }
    }


    private  void  attachFragment(String fragmentTag){

        //step1;获取Fragement管理器
        FragmentManager fragmentManager=getSupportFragmentManager();
        //开启事务
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        Fragment fragment=fragmentManager.findFragmentByTag(fragmentTag);
        if(fragment==null){
            //管理器没有这个fragment
            if(fragmentTag.equals(HOMEFRAGMENT_TAG)){
                //主页碎片
                fragment=new HomeFragment();
                fragmentTransaction.add(fragment,HOMEFRAGMENT_TAG);
                image_home.setImageResource(R.mipmap.home_selected);
                image_center.setImageResource(R.mipmap.user_unselected);
                image_order.setImageResource(R.mipmap.order_unselected);
                image_cart.setImageResource(R.mipmap.cart_unselected);
            }else if(fragmentTag.equals(CARTFRAGMENT_TAG)){
                //购物车碎片
                fragment=new CartFragment();
                fragmentTransaction.add(fragment,CARTFRAGMENT_TAG);
                image_cart.setImageResource(R.mipmap.cart_selected);
                image_home.setImageResource(R.mipmap.home_unselected);
                image_center.setImageResource(R.mipmap.user_unselected);
                image_order.setImageResource(R.mipmap.order_unselected);

            }else if(fragmentTag.equals(ORDERFRAGMENT_TAG)){
                //订单碎片
                fragment=new OrderFragment();
                fragmentTransaction.add(fragment,ORDERFRAGMENT_TAG);
                image_order.setImageResource(R.mipmap.order_selected);
                image_home.setImageResource(R.mipmap.home_unselected);
                image_center.setImageResource(R.mipmap.user_unselected);
                image_cart.setImageResource(R.mipmap.cart_unselected);
            }else if(fragmentTag.equals(CENTERFRAGMENT_TAG)){
                //个人中心碎片
                fragment=new CenterFragment();
                fragmentTransaction.add(fragment,CENTERFRAGMENT_TAG);
                image_center.setImageResource(R.mipmap.user_selected);
                image_home.setImageResource(R.mipmap.home_unselected);
                image_order.setImageResource(R.mipmap.order_unselected);
                image_cart.setImageResource(R.mipmap.cart_unselected);
            }
        }
        //添加Fragment
        fragmentTransaction.replace(R.id.content,fragment,fragmentTag);

        fragmentTransaction.commit();
    }
}
