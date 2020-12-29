package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.rest.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class KaryawanRestServiceImpl implements KaryawanRestService {

    private final WebClient webClient;

    public KaryawanRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(Setting.karyawanUrl).build();
    }

    @Override
    public Mono<BaseResponseKaryawan> getListPegawai() {
        return webClient.get().uri("/listkaryawan").retrieve().bodyToMono(BaseResponseKaryawan.class);
    }
}
