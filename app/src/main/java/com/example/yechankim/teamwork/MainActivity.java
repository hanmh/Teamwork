package com.example.yechankim.teamwork;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

public class MainActivity extends AppCompatActivity {
    private BoomMenuButton bmb;
    private int imageArray[] = {R.drawable.sublogo1, R.drawable.sublogo2, R.drawable.sublogo3};
    private String normalTextArray[] = {"틀린 코드 찾기", "코드 순서 맞히기", "코드 빈칸 채우기"};
    private String subNormalTextArray[] = {"틀린 그림 찾기와 Python 코드의 융합의 시작!", "사건 순서 추론은 한물 갔다. 이제는 코드 순서 추론!",
            "코드의 공허함을 채워주는 따뜻한 사람이 되자."};
    private int pieceColorArray[] = {R.color.yechanOrange, R.color.yechanLightGreen, R.color.yechanLightBlue};
    private ImageView mainAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 상단 상태 바 없애기 -> 하단 코드
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 상단 타이틀 바 없애기 -> 하단 2개 코드
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder =
                    makeAndSetHamButton(imageArray[i], normalTextArray[i], subNormalTextArray[i], Color.BLACK, Color.BLACK, pieceColorArray[i]);

            bmb.addBuilder(builder);
        }
        bmb.setUse3DTransformAnimation(true);

        mainAnim = (ImageView) findViewById(R.id.mainAnim);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(mainAnim);
        Glide.with(this).load(R.drawable.mainanim).into(mainAnim);
    }

    // 아래는 예찬 자체 제작 코드
    private HamButton.Builder makeAndSetHamButton(int image, String normalText, String subNormalText, int normalTextColor, int subNormalTextColor, int normalColor) {
        return
        new HamButton.Builder()
        .normalImageRes(image)
        .normalText(normalText)
        .subNormalText(subNormalText)
        .normalTextColor(normalTextColor)
        .subNormalTextColor(subNormalTextColor)
        .pieceColor(Color.WHITE)
        .normalColorRes(normalColor)
        .listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                switch (index) {
                    case 0: changeIntent(ScreenActivity0.class);
                    break;
                    case 1: changeIntent(ScreenActivity1.class);
                    break;
                    case 2: changeIntent(ScreenActivity2.class);
                    break;
                }
                // Toast.makeText(getApplicationContext(), "Clicked " + normalTextArray[index], Toast.LENGTH_SHORT).show(); // for test code
            }
        });
    }

    private void changeIntent(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this , cls);    // <- 유지보수 차원에서 개선 필요 있음.
        startActivity(intent);
    }
}