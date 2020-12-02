package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.TrainerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerDb extends JpaRepository<TrainerModel, Long>{
    Optional<TrainerModel> findById(Integer id);

    void deleteById(Integer id);
}
