package com.jobportal.controller;
import com.jobportal.dto.request.JobRequest;
import com.jobportal.dto.response.JobResponse;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllActiveJobs());
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
    @PostMapping
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobRequest request) {
        JobResponse created = jobService.createJob(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable Long id, @RequestBody JobRequest request) {
        return ResponseEntity.ok(jobService.updateJob(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/type/{jobType}")
    public ResponseEntity<List<JobResponse>> getJobsByType(@PathVariable String jobType) {
        return ResponseEntity.ok(jobService.getJobsByType(jobType));
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<List<JobResponse>> getJobsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(jobService.getJobsByLocation(location));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobResponse>> getJobsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(jobService.getJobsByUser(userId));
    }
    @GetMapping("/search")
    public ResponseEntity<List<JobResponse>> searchJobs(@RequestParam String keyword) {
        return ResponseEntity.ok(jobService.searchJobs(keyword));
    }
    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }
}
