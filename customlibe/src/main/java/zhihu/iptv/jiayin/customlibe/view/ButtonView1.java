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
public class ButtonView1 extends TextView {
    private static final String TAG = "你再瞅一个试试";


    private int defult_img, focu_img;
    ButtonViewFocu buttonViewFocu;


    public ButtonView1(Context context) {
        super(context);
    }

    public ButtonView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public interface ButtonViewFocu {
        public void onFocuse(boolean b, View view);

    }

    public ButtonViewFocu getButtonViewFocu() {
        return buttonViewFocu;
    }

    public void setButtonViewFocu1(ButtonViewFocu buttonViewFocu) {
        this.buttonViewFocu = buttonViewFocu;
    }

    public void init(final int defult_img, final int focu_img) {
        this.defult_img = defult_img;
        this.focu_img = focu_img;
        setOnFocusChangeListener(new OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {


                if (hasFocus) {
                    //选中则设置选中图
                    setBackgroundResource(focu_img);

                } else {
                    //未选中的图
                    setBackgroundResource(defult_img);


                }

                if (buttonViewFocu != null) {
                    buttonViewFocu.onFocuse(hasFocus, v);
                }


            }
        });


    }
        //focusSearch



    @Override
    public View focusSearch(int direction) {
        if (direction == View.FOCUS_UP) {
            return StaticUtils.currentSelect;
        }
        if (direction == View.FOCUS_DOWN){

        }
        return super.focusSearch(direction);
    }


}
