package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;

import com.iptv.hualu.hualunew5_17.utils.ToastUtils;

/**
 * Created by wh on 2018/1/1.
 */

public class RegiestEditTv extends android.support.v7.widget.AppCompatTextView {
    public RegiestEditTv(Context context) {
        super(context);
    }

    public RegiestEditTv(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setHint(Html.fromHtml("手机号： <font color='#7e7b8d'>请输入手机号</font>"));

    }

    boolean isYzm ;

    public void reSet(){
        isYzm = true;
        setHint(Html.fromHtml(""));

    }

    public RegiestEditTv(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



    }

//    public SearchEditTv(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if(text.toString()!=null&&onSearchTextChange!=null){
            onSearchTextChange.onChange(text.toString(),start,lengthBefore,lengthAfter);
        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    public OnSearchTextChange getOnSearchTextChange() {
        return onSearchTextChange;
    }

    public void setOnSearchTextChange(OnSearchTextChange onSearchTextChange) {
        this.onSearchTextChange = onSearchTextChange;
    }

    private OnSearchTextChange onSearchTextChange;

    public String  delete() {
        if(getText().toString()!=null&&getText().toString().length()!=0) {
            CharSequence charSequence = getText().subSequence(0, getText().length() - 1);
            setText(charSequence);
            return charSequence.toString();
        }
        return "";
    }

    public void deleteAll() {
        if(getText().toString()!=null&&getText().toString().length()!=0) {
            setText("");
        }
    }

    public interface OnSearchTextChange{

        public  void  onChange(String text, int start, int lengthBefore, int lengthAfter);

    }



    int count;
    public void setSearchText(String text){
        if(getText().length()==0){
            count = 0;
        }

        if(count>=4&&isYzm){
            ToastUtils.showToast(getContext(),"验证码最多四位，请重新检查");

        }else if(isYzm){
            count+=1;
            setText("    "+getText()+text+"        ");

        }else{
            if(!(getText().length()>=11)) {
                setText(getText() + text);
            }else{
                ToastUtils.showToast(getContext(),"只能输入11位。");
            }
        }

    }
}
