package net.plv.android.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebSettings;

import net.plv.android.webview.PLVWebViewFeedActivity;
import net.plv.android.webview.modules.feed.mock.IPLVNewResourceRequest;
import net.plv.android.webview.modules.floatablewebview.web.webviewconfig.config.PLVWebViewConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:polyv
 * Time:2024/2/5
 * Description:
 */
public class PLVFeedWebViewDemoActivity extends PLVWebViewFeedActivity {

    public static void startFeedWebViewDemoActivity(Activity activity, List<PLVWebViewConfig> resources) {
        Intent intent = new Intent(activity, PLVFeedWebViewDemoActivity.class);
        targetResources = resources;
        activity.startActivity(intent);
    }

    @Override
    protected IPLVNewResourceRequest onRequestMoreResource() {
        return new IPLVNewResourceRequest() {

            /**
             * 请求更多资源
             * @return
             */
            @Override
            public List<PLVWebViewConfig> reqeustNewResource() {

                // 加载更多的数据
                List<String> urls = new ArrayList<>(Arrays.asList(
                        //需要加载更多资源的url
                        "https://live.polyv.cn/",
                        "https://live.polyv.cn/"
                ));
                //该方法在子线程执行，可以进行耗时异步请求操作
                List<PLVWebViewConfig> configs = new ArrayList<>();
                PLVWebViewConfig config = new PLVWebViewConfig()
                        .setUa(WebSettings.getDefaultUserAgent(PLVFeedWebViewDemoActivity.this))
                        .setSystemFloatingWindow(true)
                        .setUseWebRequestPermission(false)
                        .setAllowFloatingWindow(true);
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < urls.size(); i++) {
                        configs.add(config.clone().setUrl(urls.get(i)));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return configs;
            }

            /**
             * 刷新资源
             */
            @Override
            public List<PLVWebViewConfig> refreshResource() {
                return targetResources;
            }
        };
    }

    /**
     // 在每一个WebView初始化阶段，暴露给demo层进行自定义初始化的方法
     protected void initHandleForDemo() { // 每当一个Fragment中的webview被初始化时就会调用该方法，如自定义注册事件可以在该方法进行
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
