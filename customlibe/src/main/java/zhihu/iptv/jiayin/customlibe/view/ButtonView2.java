package zhihu.iptv.jiayin.customlibe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

/**
 * Created by wh on 2018-09-12.
 */

@SuppressLint("AppCompatCustomView")
public class ButtonView2 extends TextView {
    private static final String TAG = "你再瞅一个试试";

    public ButtonView2(Context context) {
        super(context);
    }

    public ButtonView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int defult_img,focu_img;


    public ButtonViewFocu getButtonViewFocu() {
        return buttonViewFocu;
    }

    public void setButtonViewFocu(ButtonViewFocu buttonViewFocu) {
        this.buttonViewFocu = buttonViewFocu;
    }

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


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.e(TAG, "触发了" );
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.e(TAG, "长按了" );
//
//        } else if(event.getAction() == MotionEvent.ACTION_UP) {
//
//            Log.e(TAG, "快退长按了变false" );
//        }
//
//        return super.onTouchEvent(event);
//    }


    @Override
    public View focusSearch(int direction) {
        if(direction == View.FOCUS_LEFT){
            if(StaticUtils.shaixuancenterItemFocuse!=null) {
                return StaticUtils.shaixuancenterItemFocuse;
            }
        }

        return super.focusSearch(direction);
    }
}
