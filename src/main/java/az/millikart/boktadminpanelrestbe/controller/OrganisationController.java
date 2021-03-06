package az.millikart.boktadminpanelrestbe.controller;

import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.entity.Organisation;
import az.millikart.boktadminpanelrestbe.service.GroupService;
import az.millikart.boktadminpanelrestbe.service.OrganisationService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/organisations")
public class OrganisationController {
    private final OrganisationService organisationService;

    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER","ROLE_MODERATOR"})
    @GetMapping("all")
    public List<Organisation> list() {
        return organisationService.listAllOrganisations();
    }
}