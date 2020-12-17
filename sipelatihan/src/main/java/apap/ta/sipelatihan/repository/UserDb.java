package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDb extends JpaRepository<UserModel,Long> {
    UserModel findByUsername(String username);
    UserModel findByUuid(String uuid);
}
