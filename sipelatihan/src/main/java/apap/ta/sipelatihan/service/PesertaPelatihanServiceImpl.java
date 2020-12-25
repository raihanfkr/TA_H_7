package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import apap.ta.sipelatihan.repository.PesertaPelatihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PesertaPelatihanServiceImpl implements PesertaPelatihanService{
    @Autowired
    private PesertaPelatihanDb pesertaPelatihanDb;

    @Override
    public PesertaPelatihanModel updatePesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        return pesertaPelatihanDb.save(pesertaPelatihan);
    }

    @Override
    public void addPesertaPelatihan(PesertaPelatihanModel pesertaPelatihan) {
        pesertaPelatihanDb.save(pesertaPelatihan);
    }
}