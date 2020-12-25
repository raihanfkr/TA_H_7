package apap.ta.sipelatihan.service;

import java.util.List;

import apap.ta.sipelatihan.model.PelatihanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.ta.sipelatihan.model.JenisPelatihanModel;
import apap.ta.sipelatihan.repository.JenisPelatihanDb;

@Service
@Transactional
public class JenisPelatihanServiceImpl implements JenisPelatihanService {
    @Autowired
    JenisPelatihanDb jenisPelatihanDb;

    public List<JenisPelatihanModel> getJenisPelatihanList() {
        return jenisPelatihanDb.findAll();
    }

    @Override
    public JenisPelatihanModel getJenisPelatihanById(Integer id) {
        return jenisPelatihanDb.findById(id).get();
    }
}