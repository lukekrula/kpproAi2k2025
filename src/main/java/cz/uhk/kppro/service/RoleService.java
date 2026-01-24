package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Role;
import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(String id);

    Role findByName(String name);

    Role save(Role role);

    void deleteById(String id);
}
