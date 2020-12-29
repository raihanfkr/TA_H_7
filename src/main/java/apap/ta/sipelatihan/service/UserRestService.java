package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.rest.BaseResponse;
import apap.ta.sipelatihan.rest.PegawaiDTO;

public interface UserRestService {
    BaseResponse addPegawai(PegawaiDTO pegawai);

    BaseResponse getPegawai(String username);
}
