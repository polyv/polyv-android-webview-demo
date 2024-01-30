package net.plv.android.webviewdemo;

import android.app.Activity;
import android.content.Intent;

import net.plv.android.jsbridge.BridgeHandler;
import net.plv.android.jsbridge.CallBackFunction;
import net.plv.android.webview.PLVWebViewBaseActivity;
import net.plv.android.webview.modules.floatablewebview.web.webviewconfig.config.PLVWebViewConfig;

/**
 * 支持悬浮窗的WebView Activity
 * 用于展示悬浮小窗实现，保利威点直播视频H5播放，WebView基本配置，视频全屏等。
 */
public class PLVWebViewDemoActivity extends PLVWebViewBaseActivity {

    public static void startWebViewDemoActivity(Activity activity, PLVWebViewConfig config) {
        Intent intent = new Intent(activity, PLVWebViewDemoActivity.class);
        intent.putExtra(PLV_WEB_VIEW_STATUS_CONFIG, config);
        activity.startActivity(intent);
    }

    /**
    // 在初始化阶段，暴露给demo层进行自定义初始化的方法
    protected void initHandleForDemo() { // 初始化时，demo可以进行的自定义配置
    }
    */

    /**
    // 如需要监听js事件消息可以通过下面的方法进行对应处理 例如监听onShare()消息
    @Override
    public void onShare() {
        super.onShare();
    }
     */


}
