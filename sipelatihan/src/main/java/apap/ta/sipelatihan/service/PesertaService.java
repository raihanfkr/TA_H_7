package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PesertaService {
    void addPeserta(PesertaModel peserta);

    List<PesertaModel> getListPeserta();

    PesertaModel getPesertaByID(Integer id);
}