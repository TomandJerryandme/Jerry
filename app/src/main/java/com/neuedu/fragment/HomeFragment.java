package com.neuedu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.neuedu.businessproject.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);

        /**
         * attachToRoot属性中，如果未false，表示创建的子容器不添加到父容器中
         * 如果为true，则表示创建的子容器添加到父容器中，
         * 而添加到父容器中后，表现形式一般为：点击事件后，应用关闭
         * */
    }
}
