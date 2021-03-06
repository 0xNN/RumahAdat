package com.sendi.rumahadat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

public class AddData extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference database;

    // variable fields EditText dan Button
    private EditText etNamaRumah, etNamaProv, etPenjelasan;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button Logout, Simpan, Login, ShowData;

    //Membuat Kode Permintaan
    private int RC_SIGN_IN = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        etNamaRumah = findViewById(R.id.nama_rumah);
        etNamaProv = findViewById(R.id.nama_prov);
        etPenjelasan = findViewById(R.id.penjelasan);

        //Inisialisasi ID (Button)
        Logout = findViewById(R.id.logout);
        Logout.setOnClickListener(this);
        Simpan = findViewById(R.id.save);
        Simpan.setOnClickListener(this);
        Login = findViewById(R.id.login);
        Login.setOnClickListener(this);
        ShowData = findViewById(R.id.showdata);
        ShowData.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        /*
         * Mendeteksi apakah ada user yang masuk, Jika tidak, maka setiap komponen UI akan dinonaktifkan
         * Kecuali Tombol Login. Dan jika ada user yang terautentikasi, semua fungsi/komponen
         * didalam User Interface dapat digunakan, kecuali tombol Logout
         */
        if(auth.getCurrentUser() == null){
            defaultUI();
        }else {
            updateUI();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN adalah kode permintaan yang Anda berikan ke startActivityForResult, saat memulai masuknya arus.
        if (requestCode == RC_SIGN_IN) {

            //Berhasil masuk
            if (resultCode == RESULT_OK) {
                Toast.makeText(AddData.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                updateUI();
            }else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AddData.this, "Login Dibatalkan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Tampilan Default pada Activity jika user belum terautentikasi
    private void defaultUI(){
        Logout.setEnabled(false);
        Simpan.setEnabled(false);
        ShowData.setEnabled(false);
        Login.setEnabled(true);
        etNamaRumah.setEnabled(false);
        etNamaProv.setEnabled(false);
        etPenjelasan.setEnabled(false);
    }

    //Tampilan User Interface pada Activity setelah user Terautentikasi
    private void updateUI(){
        Logout.setEnabled(true);
        Simpan.setEnabled(true);
        Login.setEnabled(false);
        ShowData.setEnabled(true);
        etNamaRumah.setEnabled(true);
        etNamaProv.setEnabled(true);
        etPenjelasan.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                // Statement program untuk login/masuk
                startActivityForResult(AuthUI.getInstance()
                                .createSignInIntentBuilder()

                                //Memilih Provider atau Method masuk yang akan kita gunakan
                                .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()))
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN);
                progressBar.setVisibility(View.VISIBLE);
                break;

            case R.id.save:
                /*
                  Digunakan untuk mendapatkan referensi dan meyimpan data pada Database
                  Akan dibahas pada Tutorial Berikutnya, Mengenai Fungsi Create
                 */
                //Mendapatkan UserID dari pengguna yang Terautentikasi
                String getUserID = auth.getCurrentUser().getUid();

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getNamaRumah = etNamaRumah.getText().toString();
                String getNamaProv = etNamaProv.getText().toString();
                String getPenjelasan = etPenjelasan.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getNamaRumah) && isEmpty(getNamaProv) && isEmpty(getPenjelasan)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(AddData.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                    Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
                    Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
                    */
                    getReference.child("Admin").child(getUserID).child("Rumah").push()
                            .setValue(new RumahAdatAdd(getNamaRumah, getNamaProv, getPenjelasan))
                            .addOnSuccessListener(this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    etNamaRumah.setText("");
                                    etNamaProv.setText("");
                                    etPenjelasan.setText("");
                                    Toast.makeText(AddData.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;

            case R.id.logout:
                // Statement program untuk logout/keluar
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                Toast.makeText(AddData.this, "Logout Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                break;
            case R.id.showdata:
                // Digunakan untuk melihat data yang sudah tersimpan didalam Database
                // Akan digunakan pada Tutorial Berikutnya, mengenai penggunaan Fungsi Read
                break;
        }
    }
}
