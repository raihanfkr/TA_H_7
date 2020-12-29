package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.repository.PesertaPelatihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PesertaPelatihanServiceImpl implements PesertaPelatihanService{
    @Autowired
    private PesertaPelatihanDb pesertaPelatihanDb;

    @Autowired
    private PesertaService pesertaService;

    @Autowired
    private PelatihanService pelatihanService;

    @Override
    public PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        return pesertaPelatihanDb.save(pesertaPelatihan);
    }

    @Override
    public void addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        generateNomorPeserta(pesertaPelatihan, pesertaPelatihan.getPelatihan().getId(), pesertaPelatihan.getPeserta().getId());
        pesertaPelatihanDb.save(pesertaPelatihan);
    }

    @Override
    public void addPesertaPelatihanById(Integer idPelatihan, Integer idPeserta) {
        PesertaPelatihanModel pesertaPelatihan = new PesertaPelatihanModel();
        PelatihanModel pelatihan = pelatihanService.getPelatihanById(idPelatihan);
        PesertaModel peserta = pesertaService.getPesertaByID(idPeserta);
        generateNomorPeserta(pesertaPelatihan, idPelatihan, idPeserta);
//        id, nopeserta, peserta, pelatihan
        pesertaPelatihan.setPelatihan(pelatihan);
        pesertaPelatihan.setPeserta(peserta);
        pesertaPelatihanDb.save(pesertaPelatihan);
    }

    @Override
    public void setPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan, Integer idPeserta) {
        pesertaPelatihan.setPeserta(pesertaService.getPesertaByID(idPeserta));
    }

    @Override
    public List<PesertaPelatihanModel> getPesertaPelatihanByPelatihan(PelatihanModel pelatihan) {
        return pesertaPelatihanDb.findPesertaPelatihanModelByPelatihan(pelatihan);
    }

    @Override
    public void generateNomorPeserta(PesertaPelatihanModel pesertaPelatihan, Integer idPelatihan, Integer idPeserta) {
        StringBuilder noPesertaBuilder = new StringBuilder();
        noPesertaBuilder.append(idPelatihan);
        noPesertaBuilder.append("-");
        PesertaModel peserta = pesertaService.getPesertaByID(idPeserta);
        String departemen = peserta.getDepartemen();
        noPesertaBuilder.append(departemen.substring(0,2).toUpperCase());
        noPesertaBuilder.append("-");
        noPesertaBuilder.append(idPeserta);
        pesertaPelatihan.setNo_peserta(noPesertaBuilder.toString());
    }
}
