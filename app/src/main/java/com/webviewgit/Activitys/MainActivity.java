package com.webviewgit.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.webviewgit.R;
import com.webviewgit.Utils.ConnectionUtil;
import com.webviewgit.Utils.Links;
import com.webviewgit.Utils.SemConexaoActivity;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private boolean carregou = false; //Para não carregar a página com o fundo do SplashActivity

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final ImageView imageViewProgress = findViewById(R.id.imgProgress);
        final ProgressBar progressBar = findViewById(R.id.progress);

        //imageViewProgress.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        webView = findViewById(R.id.webviewCapa);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!ConnectionUtil.isConnected(MainActivity.this)) {
                    Intent i = new Intent(MainActivity.this, SemConexaoActivity.class);
                    startActivity(i);
                }else{
                    if(!carregou){
                        progressBar.setVisibility(View.VISIBLE);
                        //imageViewProgress.setVisibility(View.VISIBLE);
                    }else{
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
                //imageViewProgress.setVisibility(View.INVISIBLE);
                carregou = true;
            }

//            Método caso queira abrir teclado do celular ao ter um link apontando para núm de celular.
//            <a href="tel:NUM_CEL">
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                final Uri uri = request.getUrl();
//                if (uri.toString().startsWith("mailto:")) {
//                    //Handle mail Urls
//                    startActivity(new Intent(Intent.ACTION_SENDTO, uri));
//                } else if (uri.toString().startsWith("tel:")) {
//                    //Handle telephony Urls
//                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
//                } else {
//                    //Handle Web Urls
//                    view.loadUrl(uri.toString());
//                }
//                return true;
//            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        webView.loadUrl(Links.LINK_PAGINA_INICIAL);
    }
}
