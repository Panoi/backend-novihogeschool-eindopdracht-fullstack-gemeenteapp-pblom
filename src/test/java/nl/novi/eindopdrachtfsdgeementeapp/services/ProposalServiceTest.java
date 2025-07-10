package nl.novi.eindopdrachtfsdgeementeapp.services;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.*;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.ProposalRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
class ProposalServiceTest {

    @Autowired
    private ProposalService proposalService;

    @MockBean
    private ProposalRepository proposalRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testGetProposal() {
        Proposal proposal = new Proposal();
        proposal.setId(1);
        proposal.setTitle("title");
        proposal.setDescription("Test description for get proposal");

        Municipality municipality = new Municipality();
        municipality.setId(1);
        proposal.setMunicipality(municipality);

        Status status = new Status();
        status.setId(1);
        status.setStatus(ProposalStatus.PENDING);
        proposal.setStatus(status);

        User user = new User();
        user.setId(1);
        user.setFirstName("Pascal");
        user.setLastName("Blom");
        proposal.setUser(user);

        Mockito.when(proposalRepository.findById(1)).thenReturn(Optional.of(proposal));

        ProposalDto result = proposalService.getProposal(1);

        assertEquals("title", result.getTitle());
        assertEquals(proposal.getStatus().getStatus().toString(), result.getStatus());
        assertEquals("Test description for get proposal", result.getDescription());
        assertEquals(1, result.getMunicipalityId());
        assertEquals("Pascal", result.getFirstName());
        assertEquals("Blom", result.getLastName());
    }

    @Test
    void testGetAllProposal() {
        Proposal proposal1 = new Proposal();
        proposal1.setId(1);
        proposal1.setTitle("title 1");
        proposal1.setDescription("Test description for get proposal 1");

        Municipality municipality1 = new Municipality();
        municipality1.setId(1);
        proposal1.setMunicipality(municipality1);

        Status status1 = new Status();
        status1.setId(1);
        status1.setStatus(ProposalStatus.PENDING);
        proposal1.setStatus(status1);

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Pascal");
        user1.setLastName("Blom");
        proposal1.setUser(user1);

        Proposal proposal2 = new Proposal();
        proposal2.setId(2);
        proposal2.setTitle("title 2");
        proposal2.setDescription("Test description for get proposal 2");

        Municipality municipality2 = new Municipality();
        municipality2.setId(2);
        proposal2.setMunicipality(municipality2);

        Status status2 = new Status();
        status2.setId(2);
        status2.setStatus(ProposalStatus.PENDING);
        proposal2.setStatus(status2);

        User user2 = new User();
        user2.setId(1);
        user2.setFirstName("Novi");
        user2.setLastName("Hogeschool");
        proposal2.setUser(user2);

        List<Proposal> proposalList = List.of(proposal1, proposal2);
        Mockito.when(proposalRepository.findAll()).thenReturn(proposalList);

        List<ProposalDto> result = proposalService.getAllProposals();

        assertEquals(proposalList.size(), result.size());

    }

    @Test
    void testGetProposalByEmail() {
        String email = "test@email.nl";

        Proposal proposal = new Proposal();
        proposal.setId(1);
        proposal.setTitle("title");
        proposal.setDescription("Test description for get proposal");

        Municipality municipality = new Municipality();
        municipality.setId(1);
        proposal.setMunicipality(municipality);

        Status status = new Status();
        status.setId(1);
        status.setStatus(ProposalStatus.PENDING);
        proposal.setStatus(status);

        User user = new User();
        user.setId(1);
        user.setFirstName("Pascal");
        user.setLastName("Blom");
        proposal.setUser(user);

        List<Proposal> proposalList = List.of(proposal);
        Mockito.when(proposalRepository.findByUserEmail(email)).thenReturn(proposalList);

        List<ProposalDto> result = proposalService.getProposalsByEmail(email);

        assertEquals(proposalList.size(), result.size());
    }

    @Test
    void testDeleteProposal() {
        String email = "test@email.nl";

        User user = new User();
        user.setEmail(email);

        Proposal proposal = new Proposal();
        proposal.setId(1);
        proposal.setUser(user);

        Authentication auth = Mockito.mock(Authentication.class);
        Mockito.when(auth.getName()).thenReturn(email);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);


        Mockito.when(proposalRepository.findById(1)).thenReturn(Optional.of(proposal));

        proposalService.deleteProposal(1);

        Mockito.verify(proposalRepository).delete(proposal);
    }

    @Test
    void testCreateProposal() {
        String email = "test@email.nl";

        ProposalInputDto proposal = new ProposalInputDto("title", "Test description for create proposal");
        proposal.setTitle("Title");
        proposal.setDescription("Test description for create proposal");

        User user = new User();
        user.setEmail(email);

        Municipality municipality = new Municipality();
        user.setMunicipality(municipality);

        Authentication auth = Mockito.mock(Authentication.class);
        Mockito.when(auth.getName()).thenReturn(email);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Mockito.when(proposalRepository.save(Mockito.any(Proposal.class))).thenAnswer(invocation -> {
            Proposal saved = invocation.getArgument(0);
            saved.setId(1);
            return saved;
        });

        ProposalDto result = proposalService.createProposal(proposal);

        assertEquals("Title", result.getTitle());
        assertEquals("Test description for create proposal", result.getDescription());
        assertEquals(1, result.getId());
    }


}