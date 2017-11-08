package com.remember.jeanju34min.rememberme.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.remember.jeanju34min.rememberme.R;

/**
 * Created by jeanju34.min on 2017-10-31.
 */

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_wannago);

        WebView minfoView = (WebView) findViewById(R.id.web_info);

        // JAVA Script 사용한다고 설정
        minfoView.getSettings().setJavaScriptEnabled(true);
        // URL 지정
        minfoView.loadUrl("http://m.daum.net");
        // Activity 내에서 Client로 webView를 이용하도록 설정
        minfoView.setWebViewClient(new webViewClient());


    }
}

class webViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}