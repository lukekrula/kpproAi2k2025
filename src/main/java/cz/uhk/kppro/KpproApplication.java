package cz.uhk.kppro;

import cz.uhk.kppro.model.*;
import cz.uhk.kppro.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class KpproApplication {

    public static void main(String[] args) {
        SpringApplication.run(KpproApplication.class, args);
    }


    /*@Bean
    CommandLineRunner generateHash() { return args -> { System.out.println("Pass" +  new BCryptPasswordEncoder().encode("admin123")); }; }


    @Bean
    CommandLineRunner migrateUsersAndMembers(
            UserRepository userRepository,
            MemberRepository memberRepository,
            MembershipRepository membershipRepository,
            RoleRepository roleRepository,
            OrganizationRepository organizationRepository) {

        // --- PRELOAD ROLES ---
        if (roleRepository.count() == 0) {



            Role r9 = new Role();
            r9.setId(9L);
            r9.setName("ADMIN");
            roleRepository.save(r9);

            Role r10 = new Role();
            r10.setId(10L);
            r10.setName("MANAGER");
            roleRepository.save(r10);

            Role r11 = new Role();
            r11.setId(11L);
            r11.setName("VOLUNTEER");
            roleRepository.save(r11);

            Role r12 = new Role();
            r12.setId(12L);
            r12.setName("VIEWER");
            roleRepository.save(r12);

            Role r13 = new Role();
            r13.setId(13L);
            r13.setName("PARTNER_USER");
            roleRepository.save(r13);

            System.out.println("Seeded roles table.");
        }



        return args -> {

            // --- USERS ---
            List<String> lines = Files.readAllLines(Paths.get("src/main/resources/static/data/users_script.sql"));

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();

                if (!line.startsWith("INSERT INTO")) continue;

                // Collect all lines until we reach a line containing ')'
                StringBuilder sb = new StringBuilder();
                int j = i + 1;

                while (j < lines.size()) {
                    String v = lines.get(j).trim();
                    sb.append(v).append(" ");

                    if (v.contains(")")) break;
                    j++;
                }

                String valuesLine = sb.toString().trim();

                if (valuesLine.endsWith(",")) {
                    valuesLine = valuesLine.substring(0, valuesLine.length() - 1);
                }

                valuesLine = valuesLine.replace("(", "").replace(")", "");

                String[] parts = valuesLine.split(",(?=(?:[^']*'[^']*')*[^']*$)");

                for (int p = 0; p < parts.length; p++) {
                    parts[p] = parts[p].trim();
                    if ("NULL".equals(parts[p])) parts[p] = null;
                    if (parts[p] != null && parts[p].startsWith("'") && parts[p].endsWith("'")) {
                        parts[p] = parts[p].substring(1, parts[p].length() - 1);
                    }
                }

                if (parts.length < 5 || parts[0] == null || parts[0].isBlank()) {
                    System.out.println("Skipping malformed parsed parts: " + Arrays.toString(parts));
                    continue;
                }

                Long id = Long.valueOf(parts[0]);
                String password = parts[1];
                String username = parts[2];
                Long roleId = Long.valueOf(parts[3]);
                String email = parts[4];

                // ⭐ THIS WAS MISSING ⭐
                Role role = roleRepository.findById(roleId)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));

                User u = new User();
                u.setId(id);
                u.setUsername(username);
                u.setPassword(password);
                u.setEmail(email);
                u.setRole(role);

                userRepository.save(u);

                System.out.println("Saved user: " + username);
            }

            // --- MEMBERS ---
            List<String> memberLines = Files.readAllLines(Paths.get("src/main/resources/static/data/users_member.sql"));

            for (String line : memberLines) {
                if (!line.startsWith("INSERT")) continue;
                if (line.isBlank() || !line.startsWith("(")) { System.out.println("Skipping invalid values line: " + line); continue; }
                String values = line.substring(line.indexOf("VALUES") + 6)
                        .replace("(", "")
                        .replace(");", "")
                        .replace(")", "")
                        .trim();

                String[] parts = values.split(",");

                Long id = Long.valueOf(parts[0].trim());
                String email = parts[1].replace("'", "").trim();
                String username = parts[2].replace("'", "").trim();
                String roleName = parts[3].replace("'", "").trim();
                Long orgId = Long.valueOf(parts[4].trim());

                // Find the user by username
                User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found: " + username));

                // Create Member linked to User
                Member m = new Member();
                m.setId(id);
                m.setUser(user);
                m.setEmail(email.equals("NULL") ? null : email);

                memberRepository.save(m);

                // Link User → Member
                user.setMember(m);
                userRepository.save(user);

                // Create Membership
                Organization org = organizationRepository.findById(orgId)
                        .orElseThrow(() -> new RuntimeException("Org not found: " + orgId));

                Membership ms = new Membership();
                ms.setMember(m);
                ms.setOrganization(org);
                ms.setRole(MembershipRole.valueOf("COMMUNITY_MEMBER")); // old role stored here

                membershipRepository.save(ms);
            }
        };
    }
    @Bean
    CommandLineRunner loadCommunities(CommunityRepository communityRepository) {
        return args -> {

            Path file = Paths.get("src/main/resources/communities.sql");

            if (!Files.exists(file)) {
                System.out.println("No community data file found.");
                return;
            }

            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {

                if (!line.trim().startsWith("INSERT")) continue;

                // Extract VALUES(...)
                String raw = line.substring(line.indexOf("VALUES") + 6).trim();

                // Remove only the OUTER parentheses
                if (raw.startsWith("(")) raw = raw.substring(1);
                if (raw.endsWith(");")) raw = raw.substring(0, raw.length() - 2);
                if (raw.endsWith(")")) raw = raw.substring(0, raw.length() - 1);

                // Split on commas outside quotes
                String[] parts = raw.split(",(?=(?:[^']*'[^']*')*[^']*$)");
                System.out.println("RAW: " + raw);
                System.out.println("PARTS: " + Arrays.toString(parts));

                // Clean each part
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                    if (parts[i].equalsIgnoreCase("NULL")) {
                        parts[i] = null;
                    } else if (parts[i].startsWith("'") && parts[i].endsWith("'")) {
                        parts[i] = parts[i].substring(1, parts[i].length() - 1);
                    }
                }

                if (parts.length < 5) {
                    System.out.println("Malformed line: " + line);
                    continue;
                }

                Community c = new Community();
                c.setName(parts[0]);
                c.setRegistrationNumber(parts[1]);
                c.setCaseNumber(parts[2]);
                c.setFoundingDateRaw(parts[3]); // STRING
                c.setAddress(parts[4]);      // FULL address preserved
                c.setType(OrganizationType.COMMUNITY);

                communityRepository.save(c);
            }

            System.out.println("Communities loaded successfully.");
        };
    }*/



}

