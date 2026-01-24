package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Organization;
import cz.uhk.kppro.model.Role;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.OrganizationRepository;
import cz.uhk.kppro.repository.RoleRepository;
import cz.uhk.kppro.repository.UserRepository;
import cz.uhk.kppro.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           OrganizationRepository organizationRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.organizationRepository =organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return new MyUserDetails(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public void createUser(User user, String password, String roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow();

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    @Override
    public User createUser(String username, String email, String rawPassword) {

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default role USER not found"));

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(defaultRole);
        user.setPassword(passwordEncoder.encode(rawPassword));

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(User updatedUser, String newPassword, String roleId) {

        User existing = userRepository.findById(updatedUser.getId())
                .orElseThrow();

        existing.setUsername(updatedUser.getUsername());

        Role role = roleRepository.findById(roleId)
                .orElseThrow();
        existing.setRole(role);

        if (newPassword != null && !newPassword.isBlank()) {
            existing.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(existing);
    }

    public void addUserToOrganization(String userId, String orgId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        if (!user.getOrganizationIds().contains(orgId)) {
            user.getOrganizationIds().add(orgId);
            userRepository.save(user);
        }

        if (!org.getMemberUserIds().contains(userId)) {
            org.getMemberUserIds().add(userId);
            organizationRepository.save(org);
        }
    }

}
