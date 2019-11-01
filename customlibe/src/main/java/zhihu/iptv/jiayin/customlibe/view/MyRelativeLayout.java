package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.iptv.hualu.hualunew5_17.test.TestActivity;

/**
 * Created by wh on 2018/2/28.
 */

public class MyRelativeLayout extends RelativeLayout{

    public MyRelativeLayout(Context context) {
        super(context);
        init();
    }
    TestActivity activity;
    private void init() {
        activity = (TestActivity) getContext();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    public View focusSearch(View focused, int direction) {
            if(direction == View.FOCUS_DOWN){
                if(activity.myTabLayout.currentSelect == null){
                    return  activity.myTabLayout.getChildAt(0);
                }else{
                    return activity.myTabLayout.currentSelect;
                }

            }
        return super.focusSearch(focused, direction);
    }

}
