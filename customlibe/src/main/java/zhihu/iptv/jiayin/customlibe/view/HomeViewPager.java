package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class HomeViewPager extends ViewPager {


    public HomeViewPager(Context context) {
        super(context);
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {


//        if(KeyEvent.KEYCODE_DPAD_LEFT==event.getKeyCode()||KeyEvent.KEYCODE_DPAD_RIGHT==event.getKeyCode()||KeyEvent.KEYCODE_DPAD_DOWN==event.getKeyCode()){
//
//            return  false;
//        }else{
//            return super.dispatchKeyEvent(event);
//
//        }

        if (KeyEvent.KEYCODE_DPAD_CENTER != event.getKeyCode() && event.getKeyCode() != 66) {
            return false;
        } else {
            return super.dispatchKeyEvent(event);
        }


    }

    public boolean canKeepSlidingForVertically(int direction) {
        final int offset = computeVerticalScrollOffset();
        final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
        if (range == 0) return false;
        if (direction < 0) {

            return offset > 0;
        } else {
            return offset < range - 1;
        }
    }
}