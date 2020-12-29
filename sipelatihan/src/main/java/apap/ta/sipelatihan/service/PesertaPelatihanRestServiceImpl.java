package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.Repository.PelatihanDb;
import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.PesertaModel;
import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.repository.PesertaDb;
import apap.ta.sipelatihan.repository.PesertaPelatihanDb;
import apap.ta.sipelatihan.rest.KaryawanDetail;
import apap.ta.sipelatihan.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@Transactional
public class PesertaPelatihanRestServiceImpl implements PesertaPelatihanRestService{
    private final WebClient webClient;

    @Autowired
    PesertaPelatihanDb pesertaPelatihanDb;

    @Autowired
    PelatihanDb pelatihanDb;

    @Autowired
    PesertaDb pesertaDb;


    public PesertaPelatihanRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.karyawanUrl).build();
    }

    public WebClient getWebClient() {
        return webClient;
    }

    @Override
    public Mono<KaryawanDetail> assign(Integer idPelatihan) {
        Mono<KaryawanDetail> temp = this.webClient.get().uri("/api/v1/karyawanBaru").retrieve().bodyToMono(KaryawanDetail.class);
        KaryawanDetail tempDetail = temp.block();
        System.out.println("test" + tempDetail.getNama());
        return temp;
    }

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
