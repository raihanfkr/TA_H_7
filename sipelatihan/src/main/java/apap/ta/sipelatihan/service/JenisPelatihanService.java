package apap.ta.sipelatihan.service;

import java.util.List;

import apap.ta.sipelatihan.model.JenisPelatihanModel;

public interface JenisPelatihanService {
    List<JenisPelatihanModel> getJenisPelatihanList();

    JenisPelatihanModel getJenisPelatihanById(Integer id);
}