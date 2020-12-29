package apap.ta.sipelatihan.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListPesertaDetail {

    @JsonProperty("listKaryawan")
    private List<KaryawanDetail> listPeserta;

    public List<KaryawanDetail> getListPeserta() {
        return listPeserta;
    }

    public void setListPeserta(List<KaryawanDetail> listPeserta) {
        this.listPeserta = listPeserta;
    }
}
