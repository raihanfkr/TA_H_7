package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;

import java.util.List;

public interface PelatihanService {

    List<PelatihanModel> getPelatihanList();

    PelatihanModel getPelatihanById(Integer id);

    void addPelatihan(PelatihanModel pelatihan);

    void updatePelatihan(PelatihanModel pelatihan);
}
