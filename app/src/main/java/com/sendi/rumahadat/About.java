package com.sendi.rumahadat;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView tvnama, tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tvnama = findViewById(R.id.title);
        tvemail = findViewById(R.id.email);

        Typeface customFont = Typeface.createFromAsset(getAssets(),"font/LandasansMedium-ALmWA.ttf");

        tvnama.setTypeface(customFont);
        tvemail.setTypeface(customFont);
    }
}
