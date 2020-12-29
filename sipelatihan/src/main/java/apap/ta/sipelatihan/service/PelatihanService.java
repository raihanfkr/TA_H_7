package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PelatihanModel;

import java.util.List;

public interface PelatihanService {

    List<PelatihanModel> getPelatihanList();

    List<PelatihanModel> getPelatihanListPengaju();

    PelatihanModel getPelatihanById(Integer id);

    void addPelatihan(PelatihanModel pelatihan);

    PelatihanModel updatePelatihan(PelatihanModel pelatihan);

    PelatihanModel updateStatusPelatihan(PelatihanModel pelatihan);

    void deletePelatihan(Integer id);
}