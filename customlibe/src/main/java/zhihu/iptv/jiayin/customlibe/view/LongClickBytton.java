package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

/**
 * 长按连续响应的Button
 * Created by admin on 15-6-1.
 */
public class LongClickBytton extends Button  {
    /**
     * 长按连续响应的监听，长按时将会多次调用该接口中的方法直到长按结束
     */
    private LongClickRepeatListener repeatListener;
    /**
     * 间隔时间（ms）
     */
    private long intervalTime;

    private MyHandler handler;

    public LongClickBytton(Context context) {
        super(context);

        init();
    }

    public LongClickBytton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public LongClickBytton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    /**
     * 初始化监听
     */
    private void init() {
        handler = new MyHandler(this);
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new Thread(new LongClickThread()).start();
                return true;
            }
        });
    }

    /**
     * 长按时，该线程将会启动
     */
    private class LongClickThread implements Runnable {
        private int num;

        @Override
        public void run() {
            while (LongClickBytton.this.isPressed()) {
                num++;
                if (num % 5 == 0) {
                    handler.sendEmptyMessage(1);
                }

                SystemClock.sleep(intervalTime / 5);
            }
        }
    }

    /**
     * 通过handler，使监听的事件响应在主线程中进行
     */
    private static class MyHandler extends Handler {
        private WeakReference<LongClickBytton> ref;

        MyHandler(LongClickBytton button) {
            ref = new WeakReference<>(button);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            LongClickBytton button = ref.get();
            if (button != null && button.repeatListener != null) {
                button.repeatListener.repeatAction();
            }
        }
    }

    /**
     * 设置长按连续响应的监听和间隔时间，长按时将会多次调用该接口中的方法直到长按结束
     *
     * @param listener     监听
     * @param intervalTime 间隔时间（ms）
     */
    public void setLongClickRepeatListener(LongClickRepeatListener listener, long intervalTime) {
        this.repeatListener = listener;
        this.intervalTime = intervalTime;
    }

    /**
     * 设置长按连续响应的监听（使用默认间隔时间100ms），长按时将会多次调用该接口中的方法直到长按结束
     *
     * @param listener 监听
     */
    public void setLongClickRepeatListener(LongClickRepeatListener listener) {
        setLongClickRepeatListener(listener, 100);
    }

    public interface LongClickRepeatListener {
        void repeatAction();
    }
    private int defult_img,focu_img;
    ButtonViewFocu buttonViewFocu;
    public interface ButtonViewFocu{
        public void onFocuse(boolean b, View view);

    }
    public void init(final int defult_img, final int focu_img){
        this.defult_img = defult_img;
        this.focu_img = focu_img;
        setOnFocusChangeListener(new OnFocusChangeListener() {



            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    //选中则设置选中图
                    setBackgroundResource(focu_img);
                }else{
                    //未选中的图
                    setBackgroundResource(defult_img);

                }

                if(buttonViewFocu!=null){
                    buttonViewFocu.onFocuse(hasFocus,v);
                }


            }
        });


    }
}