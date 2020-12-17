package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaPelatihanModel;

import java.util.List;
import java.util.Map;

public interface PesertaPelatihanRestService {
    List<Map<String, Object>> retrieveListPesertaPelatihan(String nama_peserta);
}
