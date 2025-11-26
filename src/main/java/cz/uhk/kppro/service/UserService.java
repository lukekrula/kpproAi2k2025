package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Car;
import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    void save(User user);
}
