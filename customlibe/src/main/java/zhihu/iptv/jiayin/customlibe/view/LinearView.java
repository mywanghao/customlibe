package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

public class LinearView extends LinearLayout {
    public LinearView(Context context) {
        super(context);
    }

    public LinearView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LinearView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    View LInView;

    @Override
    public View focusSearch(View focused, int direction) {
        if (direction == View.FOCUS_DOWN) {

                StaticUtils.Lindown =  focused;
                LInView  = getFocusedChild();
                Log.e("TAA", "SDSDSADADA789:" + getFocusedChild() + "____" + StaticUtils.Lindown);

        }

        return super.focusSearch(focused, direction);
    }
}
