package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.rest.KaryawanDetail;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface PesertaPelatihanRestService {
    Mono<KaryawanDetail> assign(Integer idPelatihan);

    List<Map<String, Object>> retrieveListPesertaPelatihan(String nama_peserta);
}
