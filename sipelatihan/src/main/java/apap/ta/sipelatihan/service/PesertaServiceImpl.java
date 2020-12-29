package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.repository.PesertaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Component("pesertaServiceImpl")
public class PesertaServiceImpl implements PesertaService {
    @Autowired
    PesertaDb pesertaDb;

    @Autowired
    PesertaPelatihanService pesertaPelatihanService;

    @Override
    public void addPeserta(PesertaModel peserta) {
        pesertaDb.save(peserta);
    }

    @Override
    public List<PesertaModel> getListPeserta() {
        return pesertaDb.findAll();
    }

    @Override
    public PesertaModel getPesertaByID(Integer id) {
        return pesertaDb.findById(id).get();
    }

    @Override
    public PesertaModel getPesertaByNamaPeserta(String nama) {
        return pesertaDb.findByNama(nama);
    }

    @Override
    public List<PesertaModel> getListPesertaBaru(PelatihanModel pelatihan) {
        List<String> namaPeserta = new ArrayList<>();
        List<PesertaPelatihanModel> pesertaPelatihan = pesertaPelatihanService.getPesertaPelatihanByPelatihan(pelatihan);
        if(pesertaPelatihan.size() != 0){
            for(PesertaPelatihanModel p: pesertaPelatihan){
                namaPeserta.add(p.getPeserta().getNama());
            }
            return pesertaDb.findByNamaNotIn(namaPeserta);
        }
        return pesertaDb.findAll();
    }

}