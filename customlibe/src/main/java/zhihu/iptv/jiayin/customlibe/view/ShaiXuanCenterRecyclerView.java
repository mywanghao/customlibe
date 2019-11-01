package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

public class ShaiXuanCenterRecyclerView extends RecyclerView {
    public ShaiXuanCenterRecyclerView(Context context) {
        super(context);
    }

    public ShaiXuanCenterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaiXuanCenterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public View focusSearch(View focused, int direction) {


        Log.e("TAA","focusSearch:"+direction+"____"+focused);


        if(direction==View.FOCUS_RIGHT){
            Log.e("TAA","focusSearch:FOCUS_RIGHT");
            StaticUtils.shaixuancenterItemFocuse =  getFocusedChild();
        }



        if(direction==View.FOCUS_LEFT){

            Log.e("TAA","focusSearch:FOCUS_LEFT");
        }


//        if(direction==View.FOCUS_){
//
//        }



        return super.focusSearch(focused, direction);
    }
}
