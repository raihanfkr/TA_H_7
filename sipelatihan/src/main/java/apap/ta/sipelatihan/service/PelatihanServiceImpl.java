package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.Repository.PelatihanDb;
import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.TrainerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PelatihanServiceImpl implements PelatihanService{
    @Autowired
    PelatihanDb pelatihanDb;

    @Override
    public List<PelatihanModel> getPelatihanList() {
        return pelatihanDb.findAllByOrderById();
    }

    @Override
    public PelatihanModel getPelatihanById(Integer id) {
        return pelatihanDb.findById(id).get();
    }

    @Override
    public void addPelatihan(PelatihanModel pelatihan) {
        pelatihanDb.save(pelatihan);
    }

    @Override
    public PelatihanModel updatePelatihan(PelatihanModel pelatihan){
        pelatihanDb.save(pelatihan);
        return pelatihan;
    }
}
