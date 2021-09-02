package az.millikart.boktadminpanelrestbe.controller;

import az.millikart.boktadminpanelrestbe.entity.Branch;
import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.service.BranchService;
import az.millikart.boktadminpanelrestbe.service.GroupService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER","ROLE_MODERATOR"})
    @GetMapping("all")
    public List<Branch> list() {
        return branchService.listAllBranches();
    }
}
