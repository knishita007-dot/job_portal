package com.jobportal.controller;
import com.jobportal.dto.JobDescriptionDTO;
import com.jobportal.entity.Job;
import com.jobportal.entity.JobDescription;
import com.jobportal.repository.JobDescriptionRepository;
import com.jobportal.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/api/descriptions")
public class JobDescriptionController {
    private final JobDescriptionRepository jobDescriptionRepository;
    private final JobRepository jobRepository;
    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJobDescription(@PathVariable Long jobId) {
        Optional<JobDescription> descriptionOpt = jobDescriptionRepository.findByJobId(jobId);
        if (descriptionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        JobDescription desc = descriptionOpt.get();
        JobDescriptionDTO dto = new JobDescriptionDTO();
        dto.setJobId(desc.getJob().getId());
        dto.setDescription(desc.getDescription());
        dto.setRequirements(desc.getRequirements());
        dto.setBenefits(desc.getBenefits());
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> createJobDescription(@RequestBody JobDescriptionDTO dto) {
        return saveOrUpdateDescription(dto);
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJobDescription(@PathVariable Long jobId, @RequestBody JobDescriptionDTO dto) {
        dto.setJobId(jobId);
        return saveOrUpdateDescription(dto);
    }
    private ResponseEntity<?> saveOrUpdateDescription(JobDescriptionDTO dto) {
        if (dto.getJobId() == null) {
            return ResponseEntity.badRequest().body("jobId is required");
        }
        Optional<Job> jobOpt = jobRepository.findById(dto.getJobId());
        if (jobOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Job not found");
        }
        Optional<JobDescription> existing = jobDescriptionRepository.findByJobId(dto.getJobId());
        JobDescription desc = existing.orElse(new JobDescription());
        desc.setJob(jobOpt.get());
        desc.setDescription(dto.getDescription());
        desc.setRequirements(dto.getRequirements());
        desc.setBenefits(dto.getBenefits());
        jobDescriptionRepository.save(desc);
        return ResponseEntity.ok(dto);
    }
    public JobDescriptionController(final JobDescriptionRepository jobDescriptionRepository, final JobRepository jobRepository) {
        this.jobDescriptionRepository = jobDescriptionRepository;
        this.jobRepository = jobRepository;
    }
}
