package com.sendi.rumahadat;

import java.io.Serializable;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Herdi_WORK on 18.06.17.
 */

@IgnoreExtraProperties
public class RumahAdatAdd implements Serializable{

    private String namaRumah;
    private String namaProv;
    private String penjelasan;
    private String key;

    public RumahAdatAdd(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamaRumah() {
        return namaRumah;
    }

    public void setNamaRumah(String namaRumah) {
        this.namaRumah = namaRumah;
    }

    public String getNamaProv() {
        return namaProv;
    }

    public void setNamaProv(String namaProv) {
        this.namaProv = namaProv;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    @Override
    public String toString() {
        return " "+namaRumah+"\n" +
                " "+namaProv +"\n" +
                " "+penjelasan;
    }

    public RumahAdatAdd(String namaRumah, String namaProv, String penjelasan){
        this.namaRumah = namaRumah;
        this.namaProv = namaProv;
        this.penjelasan = penjelasan;
    }
}