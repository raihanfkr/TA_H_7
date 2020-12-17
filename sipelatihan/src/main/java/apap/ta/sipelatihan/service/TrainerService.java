package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.TrainerModel;

import java.util.List;

public interface TrainerService {
    void addTrainer(TrainerModel trainer);

    TrainerModel checkTrainer(String noKtp);

    TrainerModel updateTrainer(TrainerModel trainer);

    TrainerModel getTrainerById(Integer id);

    List<TrainerModel> getTrainerList();
}
