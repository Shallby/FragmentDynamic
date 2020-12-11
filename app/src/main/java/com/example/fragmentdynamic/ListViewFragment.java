package com.example.fragmentdynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListViewFragment extends Fragment {
    private int img[]={R.id.image1,R.id.image2,R.id.image3,R.id.image4};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listview,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageView img_fra=this.getActivity().findViewById(R.id.image_view);

        //控件的事件监听
        for (int i = 0; i <img.length ; i++) {
            //注意 这个image view是一个view 包含很多属性
            //如果不进行强制类型转换的话就是一个数字 没有办法一起用
            int keyTimes=i+1;
            TextView textView=(TextView) this.getActivity().findViewById(R.id.forButton);
            ImageView iv=(ImageView)this.getActivity().findViewById(img[i]);
            if (iv!=null){
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //进行事件的处理 逻辑处理 也就是点击了有什么操作
                        ImageView imageView1=(ImageView) v;
                        img_fra.setImageDrawable(imageView1.getDrawable());
                        textView.setText("这是第"+keyTimes+"张图");
                    }
                });
            }
        }

    }
}
