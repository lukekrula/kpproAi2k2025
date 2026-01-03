package cz.uhk.kppro.service;

import cz.uhk.kppro.dto.RegistrationDto;
import cz.uhk.kppro.model.Community;
import cz.uhk.kppro.model.Member;
import cz.uhk.kppro.model.Role;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.repository.CommunityRepository;
import cz.uhk.kppro.repository.MemberRepository;
import cz.uhk.kppro.repository.RoleRepository;
import cz.uhk.kppro.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository,
                               RoleRepository roleRepository,
                               CommunityRepository communityRepository,
                               MemberRepository memberRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.communityRepository = communityRepository;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationDto dto) {
        if (!dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // 1) Create User
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);

        // 2) Determine community
        Community community;

        if (dto.getNewCommunityName() != null && !dto.getNewCommunityName().isBlank()) {
            community = new Community();
            community.setName(dto.getNewCommunityName());
            communityRepository.save(community);   // <-- MUST BE SAVED FIRST
        } else {
            community = communityRepository.findById(dto.getCommunityId())
                    .orElseThrow(() -> new RuntimeException("Community not found"));
        }

        // 3) Create Member
        Member member = new Member();
        member.setName(dto.getUsername());
        member.setEmail(dto.getEmail());
        member.setUser(user);
        user.setMember(member);

        // 4) Link both sides
        member.getCommunities().add(community);
        community.getMembers().add(member);

        // 5) Save Member LAST
        memberRepository.save(member);
    }

}

