package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import java.util.List;
import java.util.Map;

public interface PesertaPelatihanService {

    PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    PesertaPelatihanModel addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    void generateNomorPeserta(PesertaPelatihanModel pesertaPelatihan, Integer idPelatihan, Integer idPeserta);

    List<PesertaPelatihanModel> getPesertaPelatihanByPelatihan(PelatihanModel pelatihan);

    void assignPesertaPelatihan(String[] kumpulanId, PelatihanModel pelatihanModel);

    void assignPesertaPelatihanFromPegawai(List<Map<String, Object>> listPesertaDetail, PelatihanModel pelatihanModel);

    List<PesertaPelatihanModel> getPesertaPelatihanByPeserta(PesertaModel peserta);

    void deletePesertaPelatihan(PesertaPelatihanModel a);

    PesertaPelatihanModel getPesertaPelatihanById(Long id);

}
