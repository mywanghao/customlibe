package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public View focusSearch(View focused, int direction) {



//            if (direction == View.FOCUS_UP) {
//                return StaticUtils.myTabLayout.currentSelect;
////                return activity.myTabLayout.currentSelect;
//            }


        return super.focusSearch(focused, direction);
    }
}
