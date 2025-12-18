package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Program;
import cz.uhk.kppro.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping("/create")
    public Program createProgram(
            @RequestParam long managerId,
            @RequestParam long communityId,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer amountMax
    ) {
        Program program = new Program();
        program.setName(name);
        program.setDescription(description);
        program.setAmountMax(amountMax);

        return programService.createProgram(managerId, communityId, program);
    }

    @GetMapping
    public List<Program> getAll() {
        return programService.getAll();
    }

    @GetMapping("/{id}")
    public Program get(@PathVariable long id) {
        return programService.get(id);
    }
}

