package com.webviewgit.Utils;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.webviewgit.R;

public class SemConexaoActivity extends AppCompatActivity {

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_conexao);

        LinearLayout refreshButton = findViewById(R.id.ll_refesh);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectionUtil.isConnected(SemConexaoActivity.this)){
                    onBackPressed();
                } else{
                    Toast.makeText(SemConexaoActivity.this, R.string.msg_sem_conexao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
