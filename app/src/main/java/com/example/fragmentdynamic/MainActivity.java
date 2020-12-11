package com.example.fragmentdynamic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
不论是静态调用fragment还是动态fragment
只要是一个fragment 就需要一个xml文件
静态调用是需要写好一个xml文件还要写上一个name属性

动态调用fragment的优势在于不需要提前占位 可以任一的添加到一个布局之中
只要在一个fragment的xml文件写好自己自己想要的布局
那么这个xml可以在任一的地方去调用 不限次数
注意 与静态调用类似，也需要一个fragment java类 这个类的作用和静态调用一摸一样
起到的是填充view的作用 也就是在指定好一个将要添加fragment的布局之后
利用这个java类就能将需要的填充的view填充起来

此外 mainActivity中
FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction()
        .add(R.id.listFragment,new ListViewFragment()).commitNow();
 这是创造了一个fragment 管理器 它可以实现fragment的增删改查
 简而言之：分为三步走
        1 创建一个fragment的xml文件
        2.创建一个填充class java写的 参数为将要被填充的xml名 去哪里填充 false
        3.在mainActivity中创建fragment填充器 参数为 被填充的view 谁
            来填充（java类）。结尾为commitNow
 静态调用是：1在main xml中就用一fragment占位 并且命名
           2写对应的xml文件 布局
           3.创建填充器 就是java写的一个类 作用就是填什么 去哪里填

如何判别手机横屏 以及在横屏下如何修改界面排版
也就是怎么在横屏下利用fragmentManager去删除对应的排版 在竖屏下又会自动添加回来。

 总结：    创建动态UI
 1.创建 Activity和Fragment子类
 2.定义需静态或动态添加的Fragment布局
 3.根据添加Fragment和Fragment事务的策略，定义Activity布局
 4.动态添加或者删除Fragment
 5.动态Fragment的事件处理
 6.Mainfest文件中声明Activity

在本次实验中 使用了三个fragment 重点掌握了fragment之间的通信
比如按按钮让另外的一个fragment的控件发生变化
点击一个按钮的图片让另外一个按钮的图片同时发生变化
 */
public class MainActivity extends AppCompatActivity {
    private int img[]={R.id.image1,R.id.image2,R.id.image3,R.id.image4};
    int keyTimes=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth=dm.widthPixels;
        int screenHeight=dm.heightPixels;
        FragmentManager fm=getSupportFragmentManager();
        Fragment fg=fm.findFragmentById(R.id.listFragment);
        Fragment fg2=fm.findFragmentById(R.id.listFragment2);

        if (screenWidth>screenHeight){//说明是横屏状态
            if (fg!=null||fg2!=null){
                fm.beginTransaction().remove(fg).commitNow();
                fm.beginTransaction().remove(fg2).commitNow();
            }
        }else{
            fm.beginTransaction().add(R.id.listFragment,new ListViewFragment()).commitNow();
            fm.beginTransaction().add(R.id.listFragment2,new ButtonFragment()).commitNow();
        }
    }

    public void next(View view) {
        if (keyTimes==1){
            ImageView iv=findViewById(R.id.image1);
            ImageView imageView=findViewById(R.id.image_view);
            imageView.setImageDrawable(iv.getDrawable());
            TextView tx=findViewById(R.id.forButton);
            tx.setText("这是第一张图");
        }
        if (keyTimes==2){
            ImageView iv=findViewById(R.id.image2);
            ImageView imageView=findViewById(R.id.image_view);
            imageView.setImageDrawable(iv.getDrawable());
            TextView tx=findViewById(R.id.forButton);
            tx.setText("这是第二张图");
        }
        if (keyTimes==3){
            ImageView iv=findViewById(R.id.image3);
            ImageView imageView=findViewById(R.id.image_view);
            imageView.setImageDrawable(iv.getDrawable());
            TextView tx=findViewById(R.id.forButton);
            tx.setText("这是第三张图");
        }
        keyTimes+=1;
        if (keyTimes>=4){
            keyTimes=1;
        }

    }

    public void last(View view) {
        if (keyTimes==1){
            ImageView iv=findViewById(R.id.image1);
            ImageView imageView=findViewById(R.id.image_view);
            imageView.setImageDrawable(iv.getDrawable());
            TextView tx=findViewById(R.id.forButton);
            tx.setText("这是第1张图");
        }
        if (keyTimes==2){
            ImageView iv=findViewById(R.id.image2);
            ImageView imageView=findViewById(R.id.image_view);
            imageView.setImageDrawable(iv.getDrawable());
            TextView tx=findViewById(R.id.forButton);
            tx.setText("这是第2张图");
        }
        if (keyTimes==3){
            ImageView iv=findViewById(R.id.image3);
            ImageView imageView=findViewById(R.id.image_view);
            imageView.setImageDrawable(iv.getDrawable());
            TextView tx=findViewById(R.id.forButton);
            tx.setText("这是第3张图");
        }
        keyTimes-=1;
        if (keyTimes<=0){
            keyTimes=3;
        }
    }
}