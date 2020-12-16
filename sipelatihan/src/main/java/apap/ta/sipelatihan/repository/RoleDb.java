package apap.ta.sipelatihan.repository;

import apap.ta.sipelatihan.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {

}
