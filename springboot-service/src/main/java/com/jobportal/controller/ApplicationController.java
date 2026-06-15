package com.jobportal.controller;
import com.jobportal.dto.request.ApplicationRequest;
import com.jobportal.dto.response.ApplicationResponse;
import com.jobportal.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getApplicationById(id));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(applicationService.getApplicationsByUser(userId));
    }
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }
    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(@Valid @RequestBody ApplicationRequest request) {
        ApplicationResponse created = applicationService.createApplication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
    public ApplicationController(final ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
}
