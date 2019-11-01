package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.bean.DestBean;
import com.iptv.hualu.hualunew5_17.bean.RecommentBean;
import com.iptv.hualu.hualunew5_17.listener.ListListener;
import com.iptv.hualu.hualunew5_17.network.NetWorkgk;
import com.iptv.hualu.hualunew5_17.utils.FocuseUtils;
import com.iptv.hualu.hualunew5_17.utils.StaticUtils;

import static com.iptv.hualu.hualunew5_17.network.NetWorkgk.*;

public class HomeTableLayout extends LinearLayout {

    public HomeTableLayout(Context context) {
        super(context);
        init();
    }

    public HomeTableLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HomeTableLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    public void init() {
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
//                process();
                return false;
            }
        });
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
    }


    public boolean isChild(View v) {
        for (int i = 0; i < getChildCount(); i++) {
            if (v == getChildAt(i).findViewById(R.id.tv_title)) {
                return true;
            }
        }
        return false;
    }

    RecommentBean titleList;

    public void addTitle(RecommentBean titleList, boolean refresh) {
        this.titleList = titleList;
        if (refresh) {
            removeAllViews();
            currentSelect = null;
            process(titleList);
        }

    }

    TabItemSelectListener selectListener;

    public void setTabItemSelectListener(TabItemSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    public View currentSelect = null;

    /**
     * 锁死执行一次
     */
    int selectTag;

    private void process(final RecommentBean data) {
        for (int i = 0; i < data.getData().getData().size(); i++) {
            final RecommentBean.DataBeanX.DataBean dataBean = data.getData().getData().get(i);
            View view = View.inflate(getContext(), R.layout.home_tab, null);
            final TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            //存储自己的
            view.setTag(data.getData().getData().get(i));
            view.setTag(FocuseUtils.data, i);
            Log.e("TAG", "process: " + dataBean.getName());
            tv_title.setText(dataBean.getName());

            if (i == 0) {
                tv_title.setSelected(true);
            }
            if (i == data.getData().getData().size() - 1) {

                tv_title.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        boolean ret = false;
                        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                            ret = true;
                        }
                        return ret;
                    }
                });
            }
            dataBean.setPosition(i);

            tv_title.setTag(dataBean);

            tv_title.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(final View v, boolean hasFocus) {


                    RecommentBean.DataBeanX.DataBean dataBean1 = (RecommentBean.DataBeanX.DataBean) v.getTag();

                    if (hasFocus) {
                        tv_title.requestFocus();


//                        Log.e("TAA","SDDDDD:"+v);
                        //有焦点 根据数据显示
//                        if ("2".equals(dataBean.getIs_show())) {
//
//                            ((TextView)v).setText("");
//                            Glide.with(v.getContext())
//                                    .load(dataBean.getChecked_icon())
//                                    .into(new SimpleTarget<GlideDrawable>() {
//                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//                                        @Override
//                                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                                            //加载完成后的处理
//                                            v.setBackground(resource);
////                                            imageView.setImageDrawable(resource);
//                                        }
//                                    });
//
//                        }


                        currentSelect = v;
                        StaticUtils.currentSelect = v;

                        Log.e("TAA", "DDDDD:" + v.getTag());
                        if (selectListener != null) {
                            selectListener.onSelect(v, hasFocus);
                        }

                        if (selectTag == 0) {
                            updateTextUI(v);
                            selectTag = 1;
                        }
                        tv_title.requestFocus();
                    } else {
//没焦点 根据数据显示
//                        if ("2".equals(dataBean.getIs_show())) {
//                            Glide.with(v.getContext())
//                                    .load(dataBean.getIcon())
//                                    .into(new SimpleTarget<GlideDrawable>() {
//                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//                                        @Override
//                                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                                            //加载完成后的处理
//                                            v.setBackground(resource);
////                                            imageView.setImageDrawable(resource);
//                                        }
//                                    });
//
//                        }

                    }
                }
            });
            addView(view);
        }
        setCurrentSelect(0);


    }

    public void setCurrentSelect(int position) {
        getChildAt(position).findViewById(R.id.tv_title).requestFocus();
    }

    public void updateTextUI(View v) {
        currentSelect = v;
        StaticUtils.currentSelect = v;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            View view = childAt.findViewById(R.id.tv_title);
            TextView textView = childAt.findViewById(R.id.tv_title);
            ImageView imageView = childAt.findViewById(R.id.tabselect);

            if (v == view) {

                Log.e("TAA", "更新了：：：：：:" + v.getTag());

//                textView.setTextColor(getContext().getResources().getColor(R.color.tabfocuse));
//                imageView.setVisibility(VISIBLE);
                checkFocuseMethod((TextView) view, imageView, childAt);
//                    v.setBackgroundResource(R.drawable.shape_focus_rectangle10dp);
                /*v.setPadding(0, 0, 0, 0);*/
            } else {
//                textView.setTextColor(getContext().getResources().getColor(R.color.tab_defult));
//
//                imageView.setVisibility(INVISIBLE);
                checkUnFocuseMethod((TextView) view, imageView);
                //  view.setBackgroundResource(R.drawable.shape_transparent);
                //  view.setPadding(0, 0, 0, 0);
            }
        }
    }

    public void checkUnFocuseMethod(final TextView view, ImageView imageView) {
        RecommentBean.DataBeanX.DataBean dataBean1 = (RecommentBean.DataBeanX.DataBean) view.getTag();

        //判断是图片 还是 文字
        if ("2".equals(dataBean1.getIs_show())) {
            ((TextView) view).setText("");
            Glide.with(view.getContext())
                    .load(dataBean1.getIcon())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //加载完成后的处理
                            view.setBackground(resource);
//                                            imageView.setImageDrawable(resource);
                        }
                    });


        } else {
            //文字的话就设置
            view.setTextColor(getContext().getResources().getColor(R.color.tab_defult));
            if ("1".equals(dataBean1.getChecked_font_style())) {
                imageView.setVisibility(INVISIBLE);
            } else {
                view.setBackgroundResource(0);

            }
        }
    }


    int tag;
    int lock;

    public void checkFocuseMethod(final TextView view, ImageView imageView, View childAt) {
        RecommentBean.DataBeanX.DataBean dataBean1 = (RecommentBean.DataBeanX.DataBean) view.getTag();


        //判断是图片 还是 文字
        if ("2".equals(dataBean1.getIs_show())) {
            ((TextView) view).setText("");
            Glide.with(view.getContext())
                    .load(dataBean1.getChecked_icon())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //加载完成后的处理
                            view.setBackground(resource);
//                                            imageView.setImageDrawable(resource);
                        }
                    });


        } else {
            //文字的话就设置
            if (dataBean1.getColor() != null) {
                view.setTextColor(Color.parseColor("#" + dataBean1.getColor()));
                if ("1".equals(dataBean1.getChecked_font_style())) {
                    imageView.setVisibility(VISIBLE);
                } else {
                    view.setBackgroundResource(R.drawable.home_tag_focuse);
                }
            } else {
                view.setTextColor(getContext().getResources().getColor(R.color.tabfocuse));
            }

        }
        Log.e("TAA", "滚动11222:" + childAt.getTag(FocuseUtils.data));
        if (childAt.getTag(FocuseUtils.data) != null) {
            if (Integer.parseInt(childAt.getTag(FocuseUtils.data) + "") < 10 && HomeTableLayout.this.getScrollX() == 500) {
                HomeTableLayout.this.scrollTo(0, 0);

            } else if (Integer.parseInt(childAt.getTag(FocuseUtils.data) + "") > 10 && HomeTableLayout.this.getScrollX() == 0) {
                HomeTableLayout.this.scrollTo(500, 0);

            }
        }


    }

    public static class ItemBean {
        public String title;//文本的标题
        public String id;//文本所对应的id
        public int position;//在当前列中的位置
    }

    public static interface TabItemSelectListener {
        public void onSelect(View v, boolean hasFocus);
    }

    View saveFocuse;

    @Override
    public View focusSearch(View focused, int direction) {
        if (direction == View.FOCUS_DOWN) {
            StaticUtils.testtab = focused;
            saveFocuse = getFocusedChild();
            Log.e("TAA", "SDSDSADADA-.-:" + focused + "_______" + getFocusedChild());

        }
        return super.focusSearch(focused, direction);
    }
}