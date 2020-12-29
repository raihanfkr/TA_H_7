package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;
import apap.ta.sipelatihan.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                .baseUrl(Setting.APIsipegawai).build();
    }

    @Override
    public BaseResponse addPegawai(PegawaiDTO pegawai) {
        return this.webClient
                .post()
                .uri("/api/v1/pegawai")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(pegawai)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }


    @Override
    public BaseResponse getPegawai(String username) {
        return this.webClient
                .get()
                .uri("/api/v1/pegawai/" + username)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }
}