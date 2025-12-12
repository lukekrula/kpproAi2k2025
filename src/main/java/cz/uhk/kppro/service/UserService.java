package cz.uhk.kppro.service;

import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    void updateUser(User user, String newPassword);

    User get(Long id);

    List<User> getAll();

    void delete(Long id);

    User getByEmail(String email); // optional, if you add email field
}
