package zhihu.iptv.jiayin.customlibe.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.iptv.hualu.hualunew5_17.R;

public class CusSeekBar extends SeekBar {
    private PopupWindow mPopupWindow;

    private LayoutInflater mInflater;
    private View mView;
    private int[] mPosition;

    private Context mContext;

    private final int mThumbWidth = 25;
    private TextView mTvProgress;

    private int mWidth;
    private int mHeight;
    int mProgress;
    int mViewWidth;
    int mDX;
    int mOneStep;
    int mStartX;

    public CusSeekBar(Context context) {
        super(context);
        init(context);
    }

    public CusSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CusSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mView = mInflater.inflate(R.layout.popwindow_layout, null);
        mTvProgress = (TextView) mView.findViewById(R.id.tvPop);
        mPopupWindow = new PopupWindow(mView, mView.getWidth(),
                mView.getHeight(), true);
        mPosition = new int[2];
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);

        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();

        mViewWidth = getWidth();
        mDX = mWidth - mViewWidth;
        mOneStep = mViewWidth / getMax();
        mStartX = mWidth - mDX / 2;
    }

    public void setSeekBarText(String str) {
//        if(mPopupWindow){
//
//        }

        mTvProgress.setText(str);
        mProgress = getProgress();
        try {
            mPopupWindow.showAsDropDown(this, mStartX + mOneStep * mProgress, mHeight - 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setSeekBarGone() {
//        mTvProgress.setText(str);
//        mProgress = getProgress();
//        mPopupWindow.showAsDropDown(this, mStartX + mOneStep * mProgress, mHeight - 30);
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mPopupWindow.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int getViewWidth(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredWidth();
    }

    private int getViewHeight(View v) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
        return v.getMeasuredHeight();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        int thumb_x = (this.getWidth() - mThumbWidth) * this.getProgress()
                / this.getMax();

//        int test = this.getMax() / 100;


//        int middle = mHeight / 2 - 70;//这里控制pop的Y轴位置
        int middle = (int) getY();
        super.onDraw(canvas);

        if (mPopupWindow != null) {
            try {
                this.getLocationOnScreen(mPosition);

                // 得到当前挪动的进度  得到总进度  用总进度/当前进度 得到百分比   在转换为 px百分比，得到当前px 1720 40


                float textWidth = mPosition[0] + 100;

                //获取seekbar最左端的x位置
                float left = this.getLeft();

                //进度条的刻度值
                float max = Math.abs(this.getMax());

                //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
//                float thumb = dip2px(ThreeActivity.this,15);
                float thumb = mThumbWidth;

                //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
                float average = (((float) this.getWidth()) - 2 * thumb) / max;
                //int to float
                float currentProgress = this.getProgress();
                //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
                float pox = left - textWidth / 2 + thumb + average * currentProgress;


//                int x  = ( Math.abs( thumb_x )+ mPosition[0])
//                        - (getViewWidth(mView) / 2 + mThumbWidth / 2);


                Log.e("TAA", "进度条的X：" + pox + "_________" + mPosition[0] + "___________" + this.getWidth() + "____:" + this.getMax() + "____" + this.getProgress());

                mPopupWindow.update((int) pox, (-middle) + (((int) getResources().getDimension(R.dimen.y75) * 2)),
                        getViewWidth(mView), getViewHeight(mView));
                mPopupWindow.setFocusable(false);
//                Log.e("TAA","参数::"+(thumb_x + mPosition[0] - getViewWidth(mView) / 2 + mThumbWidth / 2)+"_______"+(int)getY()+"__________"+getViewWidth(mView)+"______"+getViewHeight(mView));
//                mPopupWindow.update(thumb_x + mPosition[0] - getViewWidth(mView) / 2 + mThumbWidth / 2, (int) getResources().getDimension(R.dimen.y930)-getViewHeight(mView),getViewWidth(mView),getViewHeight(mView));

            } catch (Exception e) {

            }
        }

    }

}
