package zhihu.iptv.jiayin.customlibe.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.iptv.hualu.hualunew5_17.R;

public class ViewButtom extends Activity {
    ButtonView returnTop;
    ButtonView search1;
 String TAG = "你瞅啥：";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewbuttom);

    }



//    public void onClick3(View view) {
//        switch (view.getId()) {
//            case R.id.search1:
//                Log.e(TAG, "onClick: 搜所");
//                break;
//            case R.id.returnTop:
//                Log.e(TAG, "onClick: 顶部");
//                break;
//        }
//    }
}
