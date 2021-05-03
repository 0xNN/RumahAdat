package com.sendi.rumahadat;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Detail extends AppCompatActivity {

    ImageView imgFoto;
    TextView tvdetail;
    TextView tvnama;
    TextView tvn;
    TextView tvprovinsi;
    TextView tvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();

        Bundle extras = getIntent().getExtras();
        String nama = extras.getString("nama");
        String detail = extras.getString("detail");
        String provinsi = extras.getString("provinsi");
        String foto = extras.getString("image");
        if (extras != null) {
            Glide.with(this)
                    .load(foto)
                    .apply(new RequestOptions().override(350,350))
                    .placeholder(R.drawable.ic_error_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(imgFoto);
            tvnama.setText(nama);
            tvprovinsi.setText(provinsi);
            tvdetail.setText(detail);
            tvdetail.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    public void init() {
        imgFoto = findViewById(R.id.img_foto);
        tvnama = findViewById(R.id.nama);
        tvprovinsi = findViewById(R.id.provinsi);
        tvdetail = findViewById(R.id.det);
        tvn = findViewById(R.id.n);
        tvp = findViewById(R.id.p);

        Typeface customFont = Typeface.createFromAsset(getAssets(),"font/LandasansMedium-ALmWA.ttf");

        tvnama.setTypeface(customFont);
        tvdetail.setTypeface(customFont);
        tvprovinsi.setTypeface(customFont);
        tvn.setTypeface(customFont);
        tvp.setTypeface(customFont);
    }
}
