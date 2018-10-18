package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class TongZhiFragment extends Fragment{
    private ProgressBar pb;
    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewForTongZhi = inflater.inflate(R.layout.tongzhifragment, container, false);
        webView = (WebView)viewForTongZhi.findViewById(R.id.tongzhiwebview);
        pb = (ProgressBar)viewForTongZhi.findViewById(R.id.tongzhipb);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new myWebViewClint());

        webView.loadUrl("http://43.226.46.228/tongzhi/tongzhi1dian1.html");
        webView.addJavascriptInterface(new JsCallJavaObj() {
            @JavascriptInterface
            @Override
            public void showBigImg(String url) {
                Intent intent = new Intent(getActivity(),MaxImageView.class);
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

        return viewForTongZhi;
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
