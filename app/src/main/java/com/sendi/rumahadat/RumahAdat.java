package com.sendi.rumahadat;

public class RumahAdat {
    private String nama;
    private String detail;
    private String provinsi;
    private String foto;

    private int gambar;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public int getGambar() {
        return gambar;
    }
}
