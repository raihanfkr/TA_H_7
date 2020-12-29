package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.TrainerModel;
import apap.ta.sipelatihan.Repository.TrainerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    TrainerDb trainerDb;

    @Override
    public void addTrainer(TrainerModel trainer) {
        trainerDb.save(trainer);
    }

    @Override
    public TrainerModel updateTrainer(TrainerModel trainer){
        trainerDb.save(trainer);
        return trainer;
    }

    @Override
    public TrainerModel checkTrainer(String noKtp) {
        TrainerModel trainer = trainerDb.findByNoKtp(noKtp);
        if(trainer != null){
            return trainer;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public TrainerModel getTrainerById(Integer id) {
        return trainerDb.findTrainerModelById(id);
    }

    @Override
    public List<TrainerModel> getTrainerList() {
        return trainerDb.findAll();
    }
}