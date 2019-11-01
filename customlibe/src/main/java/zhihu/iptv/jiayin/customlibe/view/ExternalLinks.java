package zhihu.iptv.jiayin.customlibe.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iptv.hualu.hualunew5_17.R;
import com.iptv.hualu.hualunew5_17.utils.StaticUtils;
import com.iptv.hualu.hualunew5_17.utils.ToastUtils;

public class ExternalLinks extends AppCompatActivity {
    private static final String TAG = "再瞅一个试试";
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_links);
        webview = (WebView) findViewById(R.id.wv_webview);
        loadWeb();
    }

    public void loadWeb() {

        String httpurl = null;
        String url = getIntent().getStringExtra("externalURL");
        String http = "http://";
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "空的:");
            ToastUtils.showToast(this, "暂无地址");
            finish();
        } else {
            Log.e(TAG, "不是空的:");

            if (url.contains(http)) {

                httpurl = url;
                Log.e(TAG, "含有:" + httpurl);
            } else {

                httpurl = http + url;
                Log.e(TAG, "没有:" + httpurl);
            }

            //此方法可以在webview中打开链接而不会跳转到外部浏览器
            webview.setWebViewClient(new WebViewClient());
            webview.loadUrl(httpurl.trim());
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
