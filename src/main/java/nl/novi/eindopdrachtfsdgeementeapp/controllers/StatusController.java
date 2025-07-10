package nl.novi.eindopdrachtfsdgeementeapp.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.services.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(final StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatus(@PathVariable int id) {
        StatusDto status = statusService.getStatus(id);
        return ResponseEntity.ok().body(status);
    }

    @GetMapping("/proposal/{proposalId}")
    public ResponseEntity<StatusDto> getStatusByProposalId(@PathVariable int proposalId) {
        StatusDto status = statusService.getStatusByProposalId(proposalId);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/proposal/{proposalId}")
    public ResponseEntity<StatusDto> updateByProposalId(@PathVariable int proposalId, @RequestBody @Valid StatusInputDto statusInputDto) {
        StatusDto updated = statusService.updateStatusByProposalId(proposalId, statusInputDto);
        return ResponseEntity.ok(updated);
    }
}
