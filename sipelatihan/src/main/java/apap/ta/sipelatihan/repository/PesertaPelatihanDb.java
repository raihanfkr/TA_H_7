package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.PesertaPelatihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PesertaPelatihanDb extends JpaRepository<PesertaPelatihanModel, Long> {

}