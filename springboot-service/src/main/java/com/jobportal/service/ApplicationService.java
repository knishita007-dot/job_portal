package com.jobportal.service;
import com.jobportal.dto.request.ApplicationRequest;
import com.jobportal.dto.response.ApplicationResponse;
import com.jobportal.entity.Application;
import com.jobportal.entity.Job;
import com.jobportal.entity.User;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    public ApplicationResponse getApplicationById(Long id) {
        Application app = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Application", id));
        return toResponse(app);
    }
    public List<ApplicationResponse> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserIdOrderByAppliedAtDesc(userId).stream().map(this::toResponse).collect(Collectors.toList());
    }
    public List<ApplicationResponse> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobIdOrderByAppliedAtDesc(jobId).stream().map(this::toResponse).collect(Collectors.toList());
    }
    @Transactional
    public ApplicationResponse createApplication(ApplicationRequest request) {
        // Check for duplicate application
        if (applicationRepository.existsByJobIdAndUserId(request.getJobId(), request.getUserId())) {
            throw new IllegalArgumentException("You have already applied for this job");
        }
        Job job = jobRepository.findById(request.getJobId()).orElseThrow(() -> new ResourceNotFoundException("Job", request.getJobId()));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", request.getUserId()));
        if (!job.getIsActive()) {
            throw new IllegalArgumentException("This job is no longer accepting applications");
        }
        Application app = Application.builder().job(job).user(user).resumeUrl(request.getResumeUrl()).coverLetter(request.getCoverLetter()).build();
        Application saved = applicationRepository.save(app);
        return toResponse(saved);
    }
    @Transactional
    public ApplicationResponse updateStatus(Long id, String status) {
        Application app = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Application", id));
        List<String> validStatuses = List.of("submitted", "reviewing", "shortlisted", "interview", "offered", "rejected", "withdrawn");
        if (!validStatuses.contains(status.toLowerCase())) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        app.setStatus(status.toLowerCase());
        Application saved = applicationRepository.save(app);
        return toResponse(saved);
    }
    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Application", id);
        }
        applicationRepository.deleteById(id);
    }
    private ApplicationResponse toResponse(Application app) {
        return ApplicationResponse.builder().id(app.getId()).jobId(app.getJob().getId()).jobTitle(app.getJob().getTitle()).companyName(app.getJob().getCompany() != null ? app.getJob().getCompany().getName() : null).userId(app.getUser().getId()).userName(app.getUser().getFullName()).status(app.getStatus()).resumeUrl(app.getResumeUrl()).coverLetter(app.getCoverLetter()).appliedAt(app.getAppliedAt()).updatedAt(app.getUpdatedAt()).build();
    }
    public ApplicationService(final ApplicationRepository applicationRepository, final JobRepository jobRepository, final UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }
}
