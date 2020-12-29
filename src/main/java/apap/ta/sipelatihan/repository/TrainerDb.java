package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.TrainerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TrainerDb extends JpaRepository<TrainerModel, Integer>{
    TrainerModel findTrainerModelById(Integer id);

    TrainerModel findByNoKtp(String noKtp);
}