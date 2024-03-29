package apap.ta.sipelatihan.Repository;

import apap.ta.sipelatihan.model.TrainerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TrainerDb extends JpaRepository<TrainerModel, Long>{
    TrainerModel findTrainerModelById(Integer id);

    TrainerModel findByNoKtp(String noKtp);
}
