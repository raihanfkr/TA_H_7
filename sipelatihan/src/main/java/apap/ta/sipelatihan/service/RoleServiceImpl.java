package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.RoleModel;
import apap.ta.sipelatihan.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDb roleDb;

    @Override
    public RoleModel addRole(RoleModel role) {
        return roleDb.save(role);
    }

    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }
}
