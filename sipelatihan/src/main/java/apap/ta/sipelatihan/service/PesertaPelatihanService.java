package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaPelatihanModel;

import java.util.List;

public interface PesertaPelatihanService {

    PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);

    void addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan);
}