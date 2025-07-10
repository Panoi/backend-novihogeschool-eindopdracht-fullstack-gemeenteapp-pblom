package nl.novi.eindopdrachtfsdgeementeapp.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.ProposalMapper;
import nl.novi.eindopdrachtfsdgeementeapp.models.Proposal;
import nl.novi.eindopdrachtfsdgeementeapp.services.ProposalPhotoService;
import nl.novi.eindopdrachtfsdgeementeapp.services.ProposalService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/proposals")
public class ProposalController {

    private final ProposalService proposalService;
    private final ProposalPhotoService proposalPhotoService;

    public ProposalController(final ProposalService proposalService, ProposalPhotoService proposalPhotoService) {
        this.proposalService = proposalService;
        this.proposalPhotoService = proposalPhotoService;
    }

    @GetMapping
    public ResponseEntity<List<ProposalDto>> getAllProposals() {
        List<ProposalDto> allProposals = proposalService.getAllProposals();
        return ResponseEntity.ok(allProposals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalDto> getProposalById(@PathVariable int id) {
        ProposalDto proposal = proposalService.getProposal(id);
        return ResponseEntity.ok().body(proposal);
    }

    @GetMapping("user/{email}")
    public ResponseEntity<List<ProposalDto>> getProposalByEmail(@PathVariable String email) {
        List<ProposalDto> proposals = proposalService.getProposalsByEmail(email);
        return ResponseEntity.ok(proposals);
    }

    @GetMapping("/municipality")
    public ResponseEntity<List<ProposalDto>> getProposalsByMunicipality() {
        List<ProposalDto> proposals = proposalService.getProposalsByUserMunicipality();
        return ResponseEntity.ok(proposals);
    }

    @PostMapping
    public ResponseEntity<ProposalDto> createProposal(@RequestBody @Valid ProposalInputDto proposalInputDto) {
        ProposalDto newProposal = proposalService.createProposal(proposalInputDto);
        return ResponseEntity.ok(newProposal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProposal(@PathVariable int id) {
        proposalService.deleteProposal(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{proposalId}/photo")
    public ResponseEntity<ProposalDto> addPhotoToProposal(@PathVariable int proposalId,
                                                          @RequestParam("file") MultipartFile file)
            throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/proposals/")
                .path(String.valueOf(proposalId))
                .path("/photo")
                .toUriString();

        String fileName = proposalPhotoService.storeFile(file);
        Proposal proposal = proposalService.assignPhotoToProposal(fileName, proposalId);
        ProposalDto proposalDto = ProposalMapper.proposalToDto(proposal);

        return ResponseEntity.created(URI.create(url)).body(proposalDto);
    }


    @GetMapping("/{proposalId}/photo")
    public ResponseEntity<Resource> getProposalPhoto(@PathVariable("proposalId") int proposalId, HttpServletRequest request) {

        Resource resource = proposalService.getPhotoFromProposal(proposalId);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = null;
        }
        if (mimeType == null) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename())
                .body(resource);
    }
}
