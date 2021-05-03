package com.sendi.rumahadat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface customFont = Typeface.createFromAsset(getAssets(),"font/LandasansMedium-ALmWA.ttf");

        tv = findViewById(R.id.rumahadat);

        tv.setTypeface(customFont);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        }, 3000);
    }
}
