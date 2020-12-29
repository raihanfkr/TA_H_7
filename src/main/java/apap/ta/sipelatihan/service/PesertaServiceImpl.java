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
    private PesertaPelatihanService pesertaPelatihanService;

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

    @Override
    public List<PesertaModel> getPesertaTerdaftar(PelatihanModel pelatihan) {
        List<Integer> listIdPeserta = new ArrayList<>();
        List<PesertaPelatihanModel> pesertaPelatihanList = pesertaPelatihanService.getPesertaPelatihanByPelatihan(pelatihan);
        for(PesertaPelatihanModel p: pesertaPelatihanList){
            listIdPeserta.add(p.getPeserta().getId());
        }
        return pesertaDb.findByIdIn(listIdPeserta);
    }



}