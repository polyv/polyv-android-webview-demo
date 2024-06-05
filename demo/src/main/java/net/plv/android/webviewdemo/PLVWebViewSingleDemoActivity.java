package net.plv.android.webviewdemo;

import android.app.Activity;
import android.content.Intent;

import net.plv.android.webview.PLVWebViewSingleActivity;
import net.plv.android.webview.modules.floatablewebview.web.webviewconfig.config.PLVWebViewConfig;

/**
 * Author:lzj
 * Time:2023/12/29
 * Description:
 */
public class PLVWebViewSingleDemoActivity extends PLVWebViewSingleActivity {

    public static void startWebViewSingleDemoActivity(Activity activity, PLVWebViewConfig config) {
        Intent intent = new Intent(activity, PLVWebViewSingleDemoActivity.class);
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
