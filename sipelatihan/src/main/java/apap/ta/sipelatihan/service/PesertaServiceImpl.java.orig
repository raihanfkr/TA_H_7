package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.repository.PesertaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Component("pesertaServiceImpl")
public class PesertaServiceImpl implements PesertaService {
    @Autowired
    PesertaDb pesertaDb;

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

}
