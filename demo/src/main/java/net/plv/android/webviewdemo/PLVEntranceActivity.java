package net.plv.android.webviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;

import net.plv.android.webview.modules.floatablewebview.web.webviewconfig.config.PLVWebViewConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:polyv
 * Time:2024/2/6
 * Description:
 */
public class PLVEntranceActivity extends AppCompatActivity implements View.OnClickListener{
    // <editor-fold defaultstate="collapsed" desc="变量">

    private Button entranceSingleWebViewBtn;
    private Button entranceFeedWebViewBtn;

    // </editor-fold>d

    // <editor-fold defaultstate="collapsed" desc="测试数据">

    private void addTempList(List<PLVWebViewConfig> configs) {

        List<String> urls = new ArrayList(Arrays.asList(
                // 设置Feed流需要加载的URL链接
                "https://www.polyv.net/",
                "https://www.polyv.net/"
                ));

        PLVWebViewConfig config = new PLVWebViewConfig()
                .setUa(WebSettings.getDefaultUserAgent(this))
                .setSystemFloatingWindow(true)
                .setUseWebRequestPermission(false)
                .setAllowFloatingWindow(false);

        for (int i = 0; i < urls.size(); i++) {
            configs.add(config.clone().setUrl(urls.get(i)));
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="生命周期">
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plv_entrance_activity);

        entranceFeedWebViewBtn = findViewById(R.id.plv_entrance_feed_webview_btn);
        entranceSingleWebViewBtn = findViewById(R.id.plv_entrance_single_webview_btn);

        entranceFeedWebViewBtn.setOnClickListener(this);
        entranceSingleWebViewBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == entranceSingleWebViewBtn.getId()) {
            Intent intent = new Intent(this, PLVURLInputActivity.class);
            startActivity(intent);
        } else if (id == entranceFeedWebViewBtn.getId()) {
            List<PLVWebViewConfig> configs = new ArrayList<>();
            addTempList(configs);
            PLVFeedWebViewDemoActivity.startFeedWebViewDemoActivity(this, configs);
        }
    }
    // </editor-fold>
}
