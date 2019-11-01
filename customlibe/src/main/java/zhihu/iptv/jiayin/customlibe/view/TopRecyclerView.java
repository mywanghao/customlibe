package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.test.TestActivity;
import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

public class TopRecyclerView extends RecyclerView {
    public TopRecyclerView(Context context) {
        super(context);
    }

    public TopRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TopRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    TestActivity activity;


    int tagPostion;


    String savetag;
    View saveView;
    int saveaction;

    @Override
    public View focusSearch(View focused, int direction) {
//        Log.e("TAA","检测顶部12211:"+focused);
        if (("0".equals(((ViewGroup) focused.getParent()).getTag() + ""))) {
            Log.e("TAA", "检测顶部" + (direction == View.FOCUS_UP) + "___" + focused.getTag() + "___" + ("0".equals(((ViewGroup) focused.getParent()).getTag() + ""))+"_______"+focused);
            if (direction == View.FOCUS_UP) {
                return StaticUtils.myTabLayout.currentSelect;
//                return activity.myTabLayout.currentSelect;
            }
        }

        if (focused.getId() == R.id.item_bg) {
//                        MyContentLayout parent = (MyContentLayout) newFocus.getParent().getParent();
//                        if(parent instanceof FrameLayout){
//                            parent  =(ViewGroup)  newFocus.getParent().getParent().getParent().getParent();
//                        }
            ViewGroup parent = (ViewGroup) focused.getParent().getParent().getParent().getParent().getParent().getParent();
            Log.e("Test", "223222focused111131.getId();" + parent.getTag());

            if ("0".equals(parent.getTag() + "")) {//我好想知道了 这个意思是数据零
                if (direction == View.FOCUS_UP) {
                    return StaticUtils.myTabLayout.currentSelect;
//                return activity.myTabLayout.currentSelect;
                }
                if(direction == View.FOCUS_LEFT){
                    return StaticUtils.myTabLayout.currentSelect;


                }

                if(direction == View.FOCUS_RIGHT){
                    return StaticUtils.myTabLayout.currentSelect;


                }

            }

        }


        if (focused.getId() == R.id.one) {
//                        MyContentLayout parent = (MyContentLayout) newFocus.getParent().getParent();
//                        if(parent instanceof FrameLayout){
//                            parent  =(ViewGroup)  newFocus.getParent().getParent().getParent().getParent();
//                        }
            ViewGroup parent = (ViewGroup) focused.getParent().getParent();
//            Log.e("Test", "223222focused1112222131.getId();" + focused.getParent()+"_______"+((ViewGroup)focused.getParent().getParent()).getTag()+"______________");

            if ("0".equals(parent.getTag() + "")) {//我好想知道了 这个意思是数据零
                if (direction == View.FOCUS_UP) {
                    return StaticUtils.myTabLayout.currentSelect;
//                return activity.myTabLayout.currentSelect;
                }
                if(direction == View.FOCUS_LEFT){
                    return focused;


                }

                if(direction == View.FOCUS_RIGHT){
                    return focused;


                }

            }

        }


//        if(saveView!=null&&saveView==focused){
//            //重了
//            View nextFocusView = FocusFinder.getInstance().findNextFocus(this, focused, View.FOCUS_DOWN);
//            Log.e("TAA","下一个focused重了:"+(nextFocusView==focused)+"_____direction:"+direction+"___"+nextFocusView);
////            scrollTo(0,200);
////            smoothScrollToPosition(6);
//            //做去重处理
//            return nextFocusView;
//
//        }
        saveView   = focused;


        Log.e("TAA","下一个focused:"+focused+"_____direction:"+direction+"___"+((ViewGroup) focused.getParent()).getTag());
        return super.focusSearch(focused, direction);
    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            int keyCode = event.getKeyCode();
//            // 这里只考虑水平移动的情况（垂直移动相同的解决方案）
//            if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//                View focusedView = getFocusedChild();  // 获取当前获得焦点的view
//                View nextFocusView;
//                try {
//                    if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//                        // 通过findNextFocus获取下一个需要得到焦点的view
//                        nextFocusView = FocusFinder.getInstance().findNextFocus(this, focusedView, View.FOCUS_UP);
//                    } else {
//                        // 通过findNextFocus获取下一个需要得到焦点的view
//                        nextFocusView = FocusFinder.getInstance().findNextFocus(this, focusedView, View.FOCUS_DOWN);
//                    }
//                } catch (Exception e) {
//                    nextFocusView = null;
//                }
//                // 如果获取失败（也就是说需要交给系统来处理焦点， 消耗掉事件，不让系统处理， 并让先前获取焦点的view获取焦点）
//                if (nextFocusView == null) {
//                    focusedView.requestFocus();
//                    return true;
//                }
//            }
//        }
//        return super.dispatchKeyEvent(event);
//    }


}
