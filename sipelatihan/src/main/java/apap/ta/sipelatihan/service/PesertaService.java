package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaModel;
import org.springframework.stereotype.Service;

@Service
public interface PesertaService {
    void addPeserta(PesertaModel peserta);
//    List<PesertaModel> getListPerusahaan();
}
