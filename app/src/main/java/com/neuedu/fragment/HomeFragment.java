package com.neuedu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neuedu.ProductDetailActivity;
import com.neuedu.adapter.MyPagerAdapter;
import com.neuedu.businessproject.R;
import com.neuedu.utils.OkHttpCallback;
import com.neuedu.utils.OkHttpCallbackFile;
import com.neuedu.utils.OkHttpUtils;
import com.neuedu.vo.ProductListVO;
import com.neuedu.vo.ServerResponse;
import com.neuedu.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public static final int PRO_LIST = 1;
    public static final int LOAD_PIC = 2;

    ViewPager viewPager;

    List<View> list = new ArrayList<>();
    PagerAdapter pagerAdapter;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case PRO_LIST:
                    List<ProductListVO> list = (List<ProductListVO>) msg.obj;
                    //解析list的数据，并对控件进行赋值
                    render(list);
                    break;
            }
        }
    };

    private  void  render(List<ProductListVO> productListVOLsit){

        for(int i=0;i<productListVOLsit.size();i++){
            ProductListVO productListVO=productListVOLsit.get(i);
            String uri=productListVO.getMainImage();

            final ImageView imageView=new ImageView(getActivity());
            imageView.setId(productListVO.getId());
            imageView.setOnClickListener(this);
            list.add(imageView);
            OkHttpUtils.get("http://img.cdn.imbession.top/"+uri,new OkHttpCallbackFile(i){
                @Override
                public void onFinish(String status, byte[] msg,int position) {
                    Message message=mHandler.obtainMessage();
                    message.what=LOAD_PIC;
                    message.arg1=position;
                    message.obj=msg;
                    mHandler.sendMessage(message);
                }
            });
            //url okhttp-->byte[] ->BitmapFactory -->Bitmap

        }

        pagerAdapter=new MyPagerAdapter(list);
        viewPager.setAdapter(pagerAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        viewPager = view.findViewById(R.id.view_pager);
/*
        List<View> list = new ArrayList<>();
        ImageView imageView1 = new ImageView(getActivity());
        imageView1.setImageResource(R.mipmap.user_unselected);
        ImageView imageView2 = new ImageView(getActivity());
        imageView2.setImageResource(R.mipmap.order_unselected);
        ImageView imageView3 = new ImageView(getActivity());
        imageView3.setImageResource(R.mipmap.cart_unselected);
        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);*/

        //获取接口数据
        OkHttpUtils.get("http://10.25.132.94:8080/pro/product/carsouel",new OkHttpCallback(){
            @Override
            public void onFinish(String status, String msg) {
                Gson gson = new Gson();
                ServerResponse<List<ProductListVO>> serverResponse=gson.fromJson(msg, new TypeToken<ServerResponse<List<ProductListVO>>>(){}.getType());
                int status1 = serverResponse.getStatus();
                if (status1==0){
                    //成功，开始获取数据
                    List<ProductListVO> list = serverResponse.getData();
                    Message message = new Message();
                    message.what = PRO_LIST;
                    message.obj = list;
                    mHandler.sendMessage(message);
                }
            }
        });

        //给轮播图组件添加适配器
        /*MyPagerAdapter Pager = new MyPagerAdapter(list);
        viewPager.setAdapter(Pager);*/
/*
        //这是轮播图实现改变图片的事件    2019年12月28日11:12:03
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
*/

        return view;

        /**
         * attachToRoot属性中，如果未false，表示创建的子容器不添加到父容器中
         * 如果为true，则表示创建的子容器添加到父容器中，
         * 而添加到父容器中后，表现形式一般为：点击事件后，应用关闭
         * */

        /**
         * 在父组件中加载的控件有，轮播图。
         * */

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra("productId",v.getId());
        startActivity(intent);
    }
}
