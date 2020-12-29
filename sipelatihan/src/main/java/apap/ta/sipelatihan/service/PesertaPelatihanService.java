package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;

import java.util.List;

public interface PesertaPelatihanService {

    PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    void addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    void addPesertaPelatihanById(Integer idPelatihan, Integer idPeserta);

    void setPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan, Integer idPeserta);

    List<PesertaPelatihanModel> getPesertaPelatihanByPelatihan(PelatihanModel pelatihan);

    void generateNomorPeserta(PesertaPelatihanModel pesertaPelatihan, Integer idPelatihan, Integer idPeserta);
}
