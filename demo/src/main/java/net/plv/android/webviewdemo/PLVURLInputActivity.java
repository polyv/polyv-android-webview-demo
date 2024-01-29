package net.plv.android.webviewdemo;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.plv.android.webview.modules.floatablewebview.web.webviewconfig.config.PLVWebViewConfig;


/**
 * Demo 入口 Activity，用于输入url打开webview activity。
 * 该类是示例代码，仅用于展示，开发者可以不集成
 */
@SuppressLint("ShowToast")
public class PLVURLInputActivity extends Activity implements View.OnClickListener {
    // <editor-fold defaultstate="collapsed" desc="变量">
    private EditText gotoPath;
    private Button gotoBtn;
    private Button codeBtn;
    private EditText uaEt;
    private Button resetUABtn;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="生命周期">
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plv_url_input_activity);

        gotoPath = findViewById(R.id.goto_path);
        gotoBtn = findViewById(R.id.goto_btn);
        codeBtn = findViewById(R.id.code_btn);
        resetUABtn = findViewById(R.id.reset_ua_btn);
        uaEt = findViewById(R.id.ua_et);

        uaEt.setText(WebSettings.getDefaultUserAgent(this));

        gotoBtn.setOnClickListener(this);
        codeBtn.setOnClickListener(this);
        resetUABtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                PLVWebViewConfig config = new PLVWebViewConfig();
                config.setUrl(result.getContents())
                        .setUa(uaEt.getText().toString())
                        .setUseWebRequestPermission(false)
                        .setSystemFloatingWindow(true)
                        .setSupportAutoFloating(false);
                PLVWebViewDemoActivity.startWebViewDemoActivity(PLVURLInputActivity.this, config);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="点击事件">
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goto_btn) {
            String url = gotoPath.getText().toString();
            if (TextUtils.isEmpty(url)) {
                Toast.makeText(PLVURLInputActivity.this, "请输入URL", Toast.LENGTH_SHORT).show();
                return;
            }
            PLVWebViewConfig config = new PLVWebViewConfig();
            config.setUrl(url)
                    .setUa(uaEt.getText().toString())
                    .setUseWebRequestPermission(false)
                    .setSystemFloatingWindow(true)
                    .setSupportAutoFloating(false);
            PLVWebViewDemoActivity.startWebViewDemoActivity(PLVURLInputActivity.this, config);
        } else if (v.getId() == R.id.code_btn) {
            IntentIntegrator integrator = new IntentIntegrator(PLVURLInputActivity.this);
            integrator.setPrompt("扫描二维码");
            integrator.initiateScan();
        } else if (v.getId() == R.id.reset_ua_btn) {
            uaEt.setText(WebSettings.getDefaultUserAgent(this));
        }
    }
    // </editor-fold>
}
