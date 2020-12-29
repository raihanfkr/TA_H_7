package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.repository.PesertaPelatihanDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PesertaPelatihanServiceImpl implements PesertaPelatihanService{
    @Autowired
    private PesertaPelatihanDb pesertaPelatihanDb;

    @Autowired
    private PesertaService pesertaService;

    @Override
    public PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        return pesertaPelatihanDb.save(pesertaPelatihan);
    }

    @Override
    public void addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        generateNomorPeserta(pesertaPelatihan , pesertaPelatihan.getPelatihan().getId(), pesertaPelatihan.getPeserta().getId());
        pesertaPelatihanDb.save(pesertaPelatihan);
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

    @Override
    public void assignPesertaPelatihan(String[] kumpulanId, PelatihanModel pelatihanModel) {

        for(String id : kumpulanId){

            PesertaPelatihanModel pesertaPelatihan = new PesertaPelatihanModel();
            PesertaModel peserta = pesertaService.getPesertaByID(Integer.parseInt(id));
            pesertaPelatihan.setPelatihan(pelatihanModel);
            pesertaPelatihan.setPeserta(peserta);
            this.addPesertaPelatihan(pesertaPelatihan);
        }
    }

    @Override
    public void assignPesertaPelatihanFromPegawai(List<Map<String, Object>> listPesertaDetail, PelatihanModel pelatihanModel) {
        for(Map<String, Object> peserta : listPesertaDetail){
            PesertaModel pegawaiPeserta = new PesertaModel();
            for(Map.Entry<String, Object> entry : peserta.entrySet()){

                if(entry.getKey().equals("nama")){
                    pegawaiPeserta.setNama((String)entry.getValue());
                }
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());

                if(entry.getKey().equals("noTelepon")){
                    pegawaiPeserta.setNo_telepon((String)entry.getValue());
                }
                if(entry.getKey().equals(("alamat"))){
                    pegawaiPeserta.setAlamat((String)entry.getValue());
                }

            }
            pegawaiPeserta.setDepartemen("Engineer");
            pesertaService.addPeserta(pegawaiPeserta);

            PesertaPelatihanModel pesertaPelatihanAssigned = new PesertaPelatihanModel();
            pesertaPelatihanAssigned.setPeserta(pegawaiPeserta);
            pesertaPelatihanAssigned.setPelatihan(pelatihanModel);
            this.addPesertaPelatihan(pesertaPelatihanAssigned);

        }
    }


    public List<PesertaPelatihanModel> getPesertaPelatihanByPeserta(PesertaModel peserta){
        return pesertaPelatihanDb.findPesertaPelatihanModelByPeserta(peserta);
    }
}
