package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PesertaDb extends JpaRepository<PesertaModel,Long> {
//    Optional<PesertaModel> findByNoResep(Long noResep);
}

