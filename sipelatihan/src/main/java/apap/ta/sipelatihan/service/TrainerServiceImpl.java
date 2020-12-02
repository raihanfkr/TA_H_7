package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.TrainerModel;
import apap.ta.sipelatihan.repository.TrainerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    TrainerDb trainerDb;

    @Override
    public void addTrainer(TrainerModel trainer){
        trainerDb.save(trainer);
    }

    @Override
    public TrainerModel updateTrainer(TrainerModel trainer){
        trainerDb.save(trainer);
        return trainer;
    }

    @Override
    public TrainerModel getTrainerById(Integer id){
        return trainerDb.findById(id).get();
    }
}
