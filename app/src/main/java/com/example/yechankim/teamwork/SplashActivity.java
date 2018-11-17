package com.example.yechankim.teamwork;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {
    private TextView logoTitle;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 상단 상태 바 없애기 -> 하단 코드
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 상단 타이틀 바 없애기 -> 하단 2개 코드
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        // 글꼴 적용 시도 -> but 계속 fail -> 수정 필요
//        logoTitle = (TextView) findViewById(R.id.titleLogo);

//        logoTitle.setTypeface(Typeface.createFromAsset(getAssets(), "font/HANNA.ttf"));

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0, android.R.anim.fade_in);
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1800);
    }
}
