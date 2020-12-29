package apap.ta.sipelatihan.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KaryawanDetail {
    @JsonProperty("nama")
    private String nama;

    @JsonProperty("noTelepon")
    private String noTelepon;

    @JsonProperty("alamat")
    private String alamat;

//    @JsonProperty("departemen")
//    private String departemen;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

//    public String getDepartemen() {
//        return departemen;
//    }
//
//    public void setDepartemen(String departemen) {
//        this.departemen = departemen;
//    }
}
