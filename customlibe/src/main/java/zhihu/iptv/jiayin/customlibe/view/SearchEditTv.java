package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;

import com.iptv.hualu.hualunew5_17.R;

/**
 * Created by wh on 2018/1/1.
 */

public class SearchEditTv extends android.support.v7.widget.AppCompatTextView {


    public SearchEditTv(Context context) {
        super(context);
    }

    public SearchEditTv(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Drawable drawable=getResources().getDrawable(R.drawable.search_left);
        drawable.setBounds(0, 0, 20, 20);
        setCompoundDrawables(drawable, null, null, null);
//        setHint(Html.fromHtml("输入片名/人名的<font color='#308eff'>首字母</font>或<font color='#308eff'>全拼</font>"));
        setHint(Html.fromHtml("输入影片的<font color='#cccccc'>首字母</font>或<font color='#cccccc'>全拼</font>"));

    }

    public SearchEditTv(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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



    public void setSearchText(String text){
        setText(getText()+text);
    }
}
