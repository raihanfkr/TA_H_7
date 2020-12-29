package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.repository.PelatihanDb;
import apap.ta.sipelatihan.model.PelatihanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PelatihanRestServiceImpl implements PelatihanRestService {
    @Autowired
    private PelatihanDb pelatihanDb;

    @Override
    public PelatihanModel createPelatihan(PelatihanModel pelatihan) {
        return pelatihanDb.save(pelatihan);
    }
}

