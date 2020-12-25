package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.Repository.PelatihanDb;
import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.repository.PesertaDb;
import apap.ta.sipelatihan.repository.PesertaPelatihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PesertaPelatihanRestServiceImpl implements PesertaPelatihanRestService{
    @Autowired
    PesertaPelatihanDb pesertaPelatihanDb;

    @Autowired
    PelatihanDb pelatihanDb;

    @Autowired
    PesertaDb pesertaDb;

    @Override
    public List<Map<String, Object>> retrieveListPesertaPelatihan(String nama_peserta) {
        List<Map<String, Object>> listPesertaPelatihan = new ArrayList<>();
        Integer id_peserta = pesertaDb.findByNama(nama_peserta).getId();

        Optional<PesertaModel> peserta = pesertaDb.findById(id_peserta);
        List<PesertaPelatihanModel> List = peserta.get().getListPesertaPelatihan();
        for (PesertaPelatihanModel p : List ){
            PelatihanModel pelatihan = p.getPelatihan();

            Map<String,Object> map = new HashMap<>();
            map.put("id", pelatihan.getId());
            map.put("nama_pelatihan", pelatihan.getNama_pelatihan());
            map.put("tanggal_mulai", pelatihan.getTanggal_mulai());
            map.put("tanggal_selesai", pelatihan.getTanggal_selesai());
            listPesertaPelatihan.add (map);
        }
        return listPesertaPelatihan;
    }

}