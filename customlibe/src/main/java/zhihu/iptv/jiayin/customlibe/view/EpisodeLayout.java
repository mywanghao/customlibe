package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iptv.hualu.hualunew5_17.HualuYInPinAudioActivity;
import com.iptv.hualu.hualunew5_17.R;

import java.util.List;

public class EpisodeLayout extends LinearLayout {

    public EpisodeLayout(Context context) {
        super(context);
        init();
    }

    public EpisodeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EpisodeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    int itemWidth;
    int itemHeight;
    int marginRight;
    public void init(){
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                double v = (getResources().getDisplayMetrics().widthPixels-getResources().getDimension(R.dimen.x162)-9*getResources().getDimension(R.dimen.x10))*1.0/10;
                itemWidth = (int) Math.round(v);//每个Item的宽度
                itemHeight = Math.round(getResources().getDimension(R.dimen.y81));
                marginRight = Math.round(getResources().getDimension(R.dimen.x10));
                process();
                return false;
            }
        });
    }
    List<ItemBean> titleList;
    public void addTitle(List<ItemBean> titleList){
        this.titleList = titleList;
    }
    TabItemSelectListener selectListener;
    public void setTabItemSelectListener(TabItemSelectListener selectListener){
        this.selectListener = selectListener;
    }
    View currentSelect = null;
    int currentSelectTab;
    int currentAddCount;
    int intentTab=1;
    private void process() {
        //开始之前添加一个向左的修饰箭头
            if(titleList==null|| titleList.size() == 0){
                return;
            }
           addStartArrow();

            if(titleList.size() > 10){
                currentAddCount=10;
            }else{
                currentAddCount=titleList.size();
            }
            addTab(currentAddCount,currentSelectTab);
        addEndArrow();
        //更新当前的进度
        currentSelectTab+=10;

        /*while (true){
            TextView viewById = getChildAt(getChildCount() - 2).findViewById(R.id.tv_title);
            int i = Integer.parseInt(viewById.getText().toString());

            if(intentTab <= i){
                updateUIByText(intentTab);
                break;
            }else{
                processRightEnd();

            }
        }*/

    }
    View upView;
    public void setUpView(View upView){
        this.upView = upView;
    }
    @Override
    public View focusSearch(int direction) {
        if(direction == View.FOCUS_UP){
            if(upView != null){
                return upView;
            }

        }
        return super.focusSearch(direction);
    }

    public void updateUIByText(int number){
        for(int i=0;i<getChildCount();i++){
            View childAt = getChildAt(i);
            View view = childAt.findViewById(R.id.tv_title);
            if(view ==null){//父View中有箭头的图片，findViewById肯定找不到，所以要判断是否为null
                continue;
            }
            TextView textView = (TextView) view;
            if(Integer.parseInt(textView.getText().toString()) == number){
                textView.setBackgroundResource(R.drawable.shape_episode_item_bg_focus);
                currentSelectView=textView;
            }else{
                textView.setBackgroundResource(R.drawable.episode_button_unfocus);
            }
        }
    }
    public void setIntentTab(int intentTab){
        this.intentTab = intentTab;
    }
    private void addStartArrow() {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(R.drawable.press_left_arrow);

        int width = (int) getContext().getResources().getDimension(R.dimen.x29);
        int height = (int) getContext().getResources().getDimension(R.dimen.y51);
        LayoutParams params = new LayoutParams(width,height);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.rightMargin=(int) getContext().getResources().getDimension(R.dimen.x8);
        imageView.setLayoutParams(params);
        addView(imageView);
    }

    public void addEndArrow(){
        //在Item的末尾再添加一个向右的箭头
        int width = (int) getContext().getResources().getDimension(R.dimen.x29);
        int height = (int) getContext().getResources().getDimension(R.dimen.y51);
        ImageView imgEnd = new ImageView(getContext());
        imgEnd.setBackgroundResource(R.drawable.press_right_arrow);
        int widEnd = (int) getContext().getResources().getDimension(R.dimen.x29);
        int heEnd = (int) getContext().getResources().getDimension(R.dimen.y51);
        LayoutParams paramsEnd = new LayoutParams(width,height);
        paramsEnd.gravity = Gravity.CENTER_VERTICAL;
        paramsEnd.leftMargin=(int) getContext().getResources().getDimension(R.dimen.x8);
        imgEnd.setLayoutParams(paramsEnd);
        addView(imgEnd);
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(KeyEvent.ACTION_DOWN == event.getAction()){
            if(currentSelectView == getChildAt(getChildCount()-2).findViewById(R.id.tv_title) ){
                if(KeyEvent.KEYCODE_DPAD_RIGHT == event.getKeyCode()){//最后一个还在按右键
                    processRightEnd(true);
                }

            }
            if(currentSelectView ==getChildAt(1).findViewById(R.id.tv_title)){
                if(KeyEvent.KEYCODE_DPAD_LEFT == event.getKeyCode()) {//当第一个时，还在按左键
                    if(currentSelectTab-10 > 0){
                        remoeView();
                        addStartArrow();
                        addTab(10,currentSelectTab-10);
                        addEndArrow();
                        currentSelectTab-=10;
                    }else if(currentSelectTab == 0){
                        /*//从零开始
                        remoeView();
                        addStartArrow();
                        currentSelectTab = 0;
                        addTab(10,currentSelectTab);
                        currentSelectTab +=10;
                        addEndArrow();*/
                    }else{  
                        //从零开始
                        remoeView();
                        addStartArrow();
                        currentSelectTab = 0;
                        addTab(10,currentSelectTab);
                        currentSelectTab +=10;
                        addEndArrow();
                    }
                }
            }
        }

        return super.dispatchKeyEvent(event);
    }

    private void processRightEnd(boolean isSelect) {
        if(currentSelectTab+10 < titleList.size()){
            remoeView();
            addStartArrow();
            addTab(10,currentSelectTab);
            addEndArrow();
            currentSelectTab+=10;
        }else if(currentSelectTab == titleList.size()){
            //从零开始
            remoeView();
            addStartArrow();
            currentSelectTab = 0;
            addTab(10,currentSelectTab);
            currentSelectTab +=10;
            addEndArrow();
        }else{
            remoeView();
            addStartArrow();
            addTab(titleList.size()-currentSelectTab,currentSelectTab);
            addEndArrow();
            currentSelectTab+=titleList.size()-currentSelectTab;
        }
    }

    int exeCute1 = 0;
    public View currentSelectView;
    public void addTab(final int count, int currentSelect){
        for(int i = 0;i<count;i++){
            final int currentPos = i;
            View view = View.inflate(getContext(), R.layout.episode_item, null);
            final TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(titleList.get(currentSelect+i).title);
            tv_title.setTag(titleList.get(currentSelect+i));

            LayoutParams layoutParams1 = (LayoutParams) tv_title.getLayoutParams();
            layoutParams1.width=itemWidth;
            layoutParams1.height=itemHeight;
            layoutParams1.rightMargin = marginRight;
            if(currentPos == count-1){
                tv_title.setOnKeyListener(new OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
                        return false;
                    }
                });
            }
            tv_title.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        if(selectListener != null){
                            selectListener.onSelect(v,hasFocus);
                        }
                        currentSelectView = v;
                        updateTextUI(v);

                        //如果是最后一个失去了焦点


                    }else{

                    }
                }
            });
            tv_title.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    EpisodeLayout.ItemBean tag = (EpisodeLayout.ItemBean) view.getTag();
                    HualuYInPinAudioActivity listenAudio = (HualuYInPinAudioActivity) getContext();
                    listenAudio.currentPlayNumber=Integer.parseInt(tv_title.getText().toString());
                    listenAudio.requestNet(tag.id,true,1+"");
                }
            });
            addView(view);
            if(i == count-1){//当最后一个的时候，去掉右边距
                layoutParams1.rightMargin = 0;
            }

        }

    }

    private void remoeView() {
       removeAllViews();
    }

    public void updateTextUI(View v) {
        for(int i=0;i<getChildCount();i++){
            View childAt = getChildAt(i);
            View view = childAt.findViewById(R.id.tv_title);
            if(view ==null){//父View中有箭头的图片，findViewById肯定找不到，所以要判断是否为null
                continue;
            }
            if(v == view){
                v.setBackgroundResource(R.drawable.shape_episode_item_bg_focus);
            }else{
                view.setBackgroundResource(R.drawable.episode_button_unfocus);
            }
        }
    }

    public static class ItemBean{
        public String title;//文本的标题
        public int id;//文本所对应的id,根据id，获取相应的音频
        public int position;//在当前列中的位置
    }
    public static interface TabItemSelectListener{
        public void onSelect(View v, boolean hasFocus);
    }
}
