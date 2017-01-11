package com.lizengyunle.framelayouttest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class FrameLayoutTest extends Activity {

        private int currentColor = 0;
        //定义一个数组
        final int[] colors =new int[] {
                R.color.color1,
                R.color.color2,
                R.color.color3,
                R.color.color4,
                R.color.color5,
                R.color.color6
        };
        final int[] names = new int[] {
                R.id.view01,
                R.id.view02,
                R.id.view03,
                R.id.view04,
                R.id.view05,
                R.id.view06
        };
        TextView[] views = new TextView[names.length];
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                //表明消息来自本程序发送
                if(msg.what == 0x123){
                    for(int i=0;i<names.length;i++){
                        views[i].setBackgroundResource(colors[(i+currentColor) %names.length]);
                    }
                    currentColor++;
                }
                super.handleMessage(msg);
            }

        };
        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_frame_layout_test);
            for (int i=0;i<names.length;i++){
                views[i] = (TextView) findViewById(names[i]);
            }
            //定义一个线程周期性的改变currentColor变量值
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    //发送一条消息通知系统改变6个TextView组件的背景颜色
                    handler.sendEmptyMessage(0x123);
                }
            },0,200);
    }
}
