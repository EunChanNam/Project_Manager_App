package cteam.cteamproject.web.project.controller;

import cteam.cteamproject.domain.member.Member;
import cteam.cteamproject.domain.memberproject.relationservice.RelationService;
import cteam.cteamproject.domain.project.Project;
import cteam.cteamproject.domain.project.projectservice.ProjectService;
import cteam.cteamproject.web.Success;
import cteam.cteamproject.web.project.form.ProjectAddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final RelationService relationService;

    @GetMapping
    public List<Project> projects() {
        return projectService.findProjects();
    }

    @GetMapping("/{projectId}")
    public Project project(@PathVariable Long projectId) {
        return projectService.findById(projectId);
    }

    @GetMapping("/joined-members/{projectId}")
    public List<Member> getJoinedMembers(@PathVariable Long projectId) {
        return relationService.findJoinedMembers(projectId);
    }

    @PostMapping("/add") // ProjectAddFormTest 나중에 상의 후에 변경 TODO
    public Success addProject(@ModelAttribute ProjectAddForm projectAddForm) {

        Project project = new Project();
        project.setProjectName(projectAddForm.getProjectName());
        project.setDetails(projectAddForm.getDetails());
        project.setTechList(projectAddForm.getTechList());

        projectService.addProject(project);

        return new Success(true);
    }

    @GetMapping("/ING")
    public List<Project> projectsING() {
        return projectService.findINGProject();
    }

    @GetMapping("/FIN")
    public List<Project> projectsFIN() {
        return projectService.findFINProject();
    }

    @GetMapping("/RECRUIT")
    public List<Project> projectsFAIL() {
        return projectService.findRECRUITProject();
    }
}
