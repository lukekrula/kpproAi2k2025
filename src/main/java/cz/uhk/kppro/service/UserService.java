package cz.uhk.kppro.service;

import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);

    void updateUser(User user, String newPassword, String roleId);

    User get(String id);

    List<User> getAll();

    void delete(String id);

    void createUser(User user, String password, String roleId);

    User createUser(String username, String email, String rawPassword);

    Optional<User> getByEmail(String email);

    User findById(String id);
}
