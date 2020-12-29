package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.PelatihanModel;
import apap.ta.sipelatihan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PelatihanDb extends JpaRepository<PelatihanModel, Integer>{
    List<PelatihanModel> findAllByOrderById();

    List<PelatihanModel> findAllByUserPengaju(UserModel user);
}