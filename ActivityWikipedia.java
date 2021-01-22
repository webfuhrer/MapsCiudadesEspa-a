package com.example.ciudadesespaagooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWikipedia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_wikipedia);
        WebView wv=findViewById(R.id.webView);
        String ciudad=getIntent().getExtras().getString("ciudad");
        String url= "https://en.wikipedia.org/wiki/"+ciudad;

        Cliente cliente_web=new Cliente();
        wv.setWebViewClient(cliente_web);
        cliente_web.shouldOverrideUrlLoading(wv, url);
    }

    public class Cliente extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}