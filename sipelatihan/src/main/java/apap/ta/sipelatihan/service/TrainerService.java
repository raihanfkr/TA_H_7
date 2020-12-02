package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.TrainerModel;

import java.util.Optional;

public interface TrainerService {
    void addTrainer(TrainerModel trainer);

    TrainerModel checkTrainer(String noKtp);

    TrainerModel getTrainerById(Integer id);
}
