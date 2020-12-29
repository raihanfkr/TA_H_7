package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.rest.ListPesertaDetail;

import java.util.List;
import java.util.Map;

public interface PesertaPelatihanService {

    PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    void addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    void generateNomorPeserta(PesertaPelatihanModel pesertaPelatihan, Integer idPelatihan, Integer idPeserta);

    List<PesertaPelatihanModel> getPesertaPelatihanByPelatihan(PelatihanModel pelatihan);

    void assignPesertaPelatihan(String[] kumpulanId, PelatihanModel pelatihanModel);

    void assignPesertaPelatihanFromPegawai(List<Map<String, Object>> listPesertaDetail, PelatihanModel pelatihanModel);

    List<PesertaPelatihanModel> getPesertaPelatihanByPeserta(PesertaModel peserta);
}
