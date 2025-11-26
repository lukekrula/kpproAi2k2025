package cz.uhk.kppro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import cz.uhk.kppro.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

