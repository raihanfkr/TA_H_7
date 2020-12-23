package apap.ta.sipelatihan.Repository;

import apap.ta.sipelatihan.model.PelatihanModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PelatihanDb extends JpaRepository<PelatihanModel, Integer>{
    List<PelatihanModel> findAllByOrderById();
}
