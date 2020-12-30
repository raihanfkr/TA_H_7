package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PesertaService {
    void addPeserta(PesertaModel peserta);

    List<PesertaModel> getListPeserta();

    PesertaModel getPesertaByID(Integer id);

    PesertaModel getPesertaByNamaPeserta(String nama);

    List<PesertaModel> getListPesertaBaru(PelatihanModel pelatihan);

    List<PesertaModel> getPesertaTerdaftar(PelatihanModel pelatihan);
}