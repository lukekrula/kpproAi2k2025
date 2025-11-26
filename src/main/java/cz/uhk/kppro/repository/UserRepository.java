package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Car;
import cz.uhk.kppro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByUsername(String username);
}
