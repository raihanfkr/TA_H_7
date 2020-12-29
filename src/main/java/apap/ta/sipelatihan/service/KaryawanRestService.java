package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.rest.BaseResponseKaryawan;
import apap.ta.sipelatihan.rest.KaryawanDetail;
import apap.ta.sipelatihan.rest.ListPesertaDetail;
import reactor.core.publisher.Mono;

import java.util.List;

public interface KaryawanRestService {
    Mono<BaseResponseKaryawan> getListPegawai();
}
