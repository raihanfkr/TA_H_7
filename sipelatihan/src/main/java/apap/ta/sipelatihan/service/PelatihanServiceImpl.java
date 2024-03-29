package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.Repository.PelatihanDb;
import apap.ta.sipelatihan.model.PelatihanModel;
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

}
