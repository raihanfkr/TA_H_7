package apap.ta.sipelatihan.Repository;

import apap.ta.sipelatihan.model.TrainerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD

public interface TrainerDb extends JpaRepository<TrainerModel, Long>{
    TrainerModel findTrainerModelById(Integer id);

=======

public interface TrainerDb extends JpaRepository<TrainerModel, Integer>{
    TrainerModel findTrainerModelById(Integer id);

>>>>>>> d0569a03cd4c11f1ff7158281de513d17028a5de
    TrainerModel findByNoKtp(String noKtp);
}
