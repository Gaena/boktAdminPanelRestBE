package az.millikart.boktadminpanelrestbe.service;

import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.entity.Organisation;
import az.millikart.boktadminpanelrestbe.exception.GroupNotFoundException;
import az.millikart.boktadminpanelrestbe.exception.OrganisationNotFoundException;
import az.millikart.boktadminpanelrestbe.repository.GroupRepository;
import az.millikart.boktadminpanelrestbe.repository.OrganisationRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrganisationService {
    private final OrganisationRepository organisationRepository;

    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    public List<Organisation> listAllOrganisations() {
        List<Organisation> organisationList =  organisationRepository.findAll();
        if (organisationList.size() < 1) {
            throw new OrganisationNotFoundException("No organisations");
        }
        return organisationList;

    }
}
