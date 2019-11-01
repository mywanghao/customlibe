package zhihu.iptv.jiayin.customlibe.view;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.iptv.hualu.hualunew5_17.utils.StaticUtils;


public class PersonalRecyclerView extends RecyclerView {
    public PersonalRecyclerView(Context context) {
        super(context);
    }

    public PersonalRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PersonalRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    @Override
    public View focusSearch(View focused, int direction) {
        if (direction == View.FOCUS_UP  && StaticUtils.RecyclerItem < 4 ) {

          return StaticUtils.Lindown;
        }
        return super.focusSearch(focused, direction);
    }
}
