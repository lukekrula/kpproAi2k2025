package cz.uhk.kppro.service;

import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);


    void updateUser(User user, String newPassword, long roleId);

    User get(Long id);

    List<User> getAll();

    void delete(Long id);

    void createUser(User user, String password, long roleId);


    Optional<User> getByEmail(String email);

    User findById(Long id);
}
