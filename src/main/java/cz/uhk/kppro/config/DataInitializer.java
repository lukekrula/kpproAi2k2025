package cz.uhk.kppro.config;

import cz.uhk.kppro.model.*;
import cz.uhk.kppro.repository.*;
import cz.uhk.kppro.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  /*  @Bean
    CommandLineRunner sampleData(
            CommunityRepository communityRepo,
            MemberRepository memberRepo,
            ProgramRepository programRepo,
            TaskService taskService
    ) {
        return args -> {

            // 1. Load an existing community from your DB
            Community community = communityRepo.findAll().stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No communities exist in DB!"));

            // 2. Create a member
            Member manager = new Member();
            manager.setName("John Manager");
            manager.setEmail("john@example.com");
            manager.setRole("manager");

            // 3. Assign the existing community
            manager.getCommunities().add(community);
            memberRepo.save(manager);

            // 4. Create a program
            Program program = new Program();
            program.setName("Sample Program");
            program.setDescription("Demo program");
            program.setCreator(community);     // existing community
            program.setManager(manager);
            programRepo.save(program);

            // 5. Create tasks
            Task root = new Task("Root Task");
            taskService.createTask(program.getId(), manager.getId(), root);

            taskService.addSubtask(root.getId(), new Task("Subtask A"));
            taskService.addSubtask(root.getId(), new Task("Subtask B"));

            System.out.println("✅ Sample data created using existing community ID: " + community.getId());
        };
    }*/
}
