package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.rest.LaporanDetail;
import apap.ta.sipelatihan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;


@Service
@Transactional
public class LaporanRestServiceImpl implements LaporanRestService {
    private final WebClient webClient;

    public LaporanRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.laporanUrl).build();
    }

    @Override
    public String postLaporan(LaporanDetail laporan) {
        return this.webClient.post().uri("/peserta/laporan")
                .header("Content-Type", "application/json")
                .syncBody(laporan)
                .retrieve()
                .bodyToMono(String.class).block();
    }
}
