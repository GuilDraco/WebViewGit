package com.webviewgit.Activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webviewgit.R;
import com.webviewgit.Utils.ConnectionUtil;
import com.webviewgit.Utils.SemConexaoActivity;

public class SplashActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        verificarConexao();
    }

    public void verificarConexao() {
        if (!ConnectionUtil.isConnected(SplashActivity.this)) {
            Intent i = new Intent(SplashActivity.this, SemConexaoActivity.class);
            startActivity(i);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarConexao();
    }
}
