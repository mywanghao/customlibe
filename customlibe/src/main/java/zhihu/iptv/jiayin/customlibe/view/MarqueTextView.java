package zhihu.iptv.jiayin.customlibe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class MarqueTextView extends TextView {
    public MarqueTextView(Context context) {
        super(context);
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

  public boolean isFocused(){
        return true;
  }
}
