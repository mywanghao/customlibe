package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

public class FocusTextView extends TextView {
    public FocusTextView(Context context) {
        super(context);
    }

    public FocusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    @Override
    public View focusSearch(int direction) {
//        setNextFocusUpId(StaticUtils.currentSelect.getId());
//        clearFocus();

            if (direction == View.FOCUS_UP) {

                Log.e("TAG", "focusSearch: "+StaticUtils.currentSelect+"____"+direction );
                return StaticUtils.currentSelect;
//                return activity.myTabLayout.currentSelect;
            }

        return super.focusSearch(direction);
    }












}
