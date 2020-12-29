package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PesertaDb extends JpaRepository<PesertaModel, Integer> {
    PesertaModel findByNama(String nama);

    List<PesertaModel> findByNamaNotIn(List<String> nama);

    Optional<PesertaModel> findById(Integer id);
}