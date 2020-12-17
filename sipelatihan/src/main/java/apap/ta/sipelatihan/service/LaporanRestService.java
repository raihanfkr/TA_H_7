package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.rest.LaporanDetail;
import reactor.core.publisher.Mono;

public interface LaporanRestService {
    Mono<String> postLaporan (LaporanDetail laporan);
}