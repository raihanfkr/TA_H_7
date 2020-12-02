package apap.ta.sipelatihan.service;

import apap.ta.sipelatihan.model.RoleModel;

import java.util.List;

public interface RoleService {
    RoleModel addRole(RoleModel role);
    List<RoleModel> findAll();
}