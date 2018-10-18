package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CommonWebViewActivity extends AppCompatActivity {
    private ProgressBar pb;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web_view);
        webView = (WebView)findViewById(R.id.commonwebview);
        pb = (ProgressBar)findViewById(R.id.pb);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebChromeClient(new myWebViewClint());
        Intent intent = getIntent();
        String data = intent.getStringExtra("add");

        webView.loadUrl(data);
        webView.addJavascriptInterface(new JsCallJavaObj() {
            @JavascriptInterface
            @Override
            public void showBigImg(String url) {
                Intent intent = new Intent(CommonWebViewActivity.this,MaxImageView.class);
                intent.putExtra("add", url);
                startActivity(intent);
            }
        },"jsCallJavaObj");
        webView.setWebViewClient(new WebViewClient(){
            @JavascriptInterface

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    if (url.contains("tel:")) {
                        String phone = url.substring(4);
                        Intent intent3 = new Intent(Intent.ACTION_DIAL);
                        intent3.setData(Uri.parse("tel:" + phone));
                        startActivity(intent3);
                        return true;
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                webView.loadUrl(url);
                return true;

                //return super.shouldOverrideUrlLoading(view, url);
            }

           @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setWebImageClick(view);
            }

            private void setWebImageClick(WebView view) {
                String jsCode="javascript:(function(){" +
                        "var imgs=document.getElementsByTagName(\"img\");" +
                        "for(var i=0;i<imgs.length;i++){" +
                        "imgs[i].onclick=function(){" +
                        "window.jsCallJavaObj.showBigImg(this.src);" +
                        "}}})()";
                webView.loadUrl(jsCode);
            }
        });


    }

    private interface JsCallJavaObj{
        void showBigImg(String url);
    }

    private class myWebViewClint extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            pb.setProgress(newProgress);
            if(newProgress==100){
                pb.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
