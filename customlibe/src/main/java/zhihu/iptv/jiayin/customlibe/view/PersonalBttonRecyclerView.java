package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

public class PersonalBttonRecyclerView extends RecyclerView {
    public PersonalBttonRecyclerView(Context context) {
        super(context);
    }

    public PersonalBttonRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonalBttonRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public View focusSearch(View focused, int direction) {

        if (direction == View.FOCUS_UP) {
            Log.e("TAG", "按键上执行了" + StaticUtils.personalbutton );

            if (StaticUtils.personalbutton != null) {

                return StaticUtils.personalbutton;
            }


        }

        return super.focusSearch(focused, direction);
    }
}
