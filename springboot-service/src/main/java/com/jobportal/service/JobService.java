package com.jobportal.service;
import com.jobportal.dto.request.JobRequest;
import com.jobportal.dto.response.JobResponse;
import com.jobportal.entity.*;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    public List<JobResponse> getAllActiveJobs() {
        return jobRepository.findByIsActiveTrueOrderByCreatedAtDesc().stream().map(this::toResponse).collect(Collectors.toList());
    }
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job", id));
        return toResponse(job);
    }
    @Transactional
    public JobResponse createJob(JobRequest request) {
        Company company = companyRepository.findById(request.getCompanyId()).orElseThrow(() -> new ResourceNotFoundException("Company", request.getCompanyId()));
        User postedBy = userRepository.findById(request.getPostedBy()).orElseThrow(() -> new ResourceNotFoundException("User", request.getPostedBy()));
        Set<Skill> skills = new HashSet<>();
        if (request.getSkillIds() != null && !request.getSkillIds().isEmpty()) {
            skills = new HashSet<>(skillRepository.findByIdIn(request.getSkillIds()));
        }
        Job job = Job.builder().title(request.getTitle()).company(company).location(request.getLocation()).salaryMin(request.getSalaryMin()).salaryMax(request.getSalaryMax()).jobType(request.getJobType()).experience(request.getExperience()).postedBy(postedBy).skills(skills).build();
        Job saved = jobRepository.save(job);
        return toResponse(saved);
    }
    @Transactional
    public JobResponse updateJob(Long id, JobRequest request) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job", id));
        if (request.getTitle() != null) job.setTitle(request.getTitle());
        if (request.getLocation() != null) job.setLocation(request.getLocation());
        if (request.getSalaryMin() != null) job.setSalaryMin(request.getSalaryMin());
        if (request.getSalaryMax() != null) job.setSalaryMax(request.getSalaryMax());
        if (request.getJobType() != null) job.setJobType(request.getJobType());
        if (request.getExperience() != null) job.setExperience(request.getExperience());
        if (request.getCompanyId() != null) {
            Company company = companyRepository.findById(request.getCompanyId()).orElseThrow(() -> new ResourceNotFoundException("Company", request.getCompanyId()));
            job.setCompany(company);
        }
        if (request.getSkillIds() != null) {
            Set<Skill> skills = new HashSet<>(skillRepository.findByIdIn(request.getSkillIds()));
            job.setSkills(skills);
        }
        Job saved = jobRepository.save(job);
        return toResponse(saved);
    }
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResourceNotFoundException("Job", id);
        }
        jobRepository.deleteById(id);
    }
    public List<JobResponse> getJobsByType(String jobType) {
        return jobRepository.findByJobTypeAndIsActiveTrue(jobType).stream().map(this::toResponse).collect(Collectors.toList());
    }
    public List<JobResponse> getJobsByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCaseAndIsActiveTrue(location).stream().map(this::toResponse).collect(Collectors.toList());
    }
    public List<JobResponse> getJobsByUser(Long userId) {
        return jobRepository.findByPostedByUserId(userId).stream().map(this::toResponse).collect(Collectors.toList());
    }
    public List<JobResponse> searchJobs(String keyword) {
        return jobRepository.searchByKeyword(keyword).stream().map(this::toResponse).collect(Collectors.toList());
    }
    private JobResponse toResponse(Job job) {
        return JobResponse.builder().id(job.getId()).title(job.getTitle()).companyName(job.getCompany() != null ? job.getCompany().getName() : null).companyId(job.getCompany() != null ? job.getCompany().getId() : null).location(job.getLocation()).salaryMin(job.getSalaryMin()).salaryMax(job.getSalaryMax()).jobType(job.getJobType()).experience(job.getExperience()).isActive(job.getIsActive()).skills(job.getSkills().stream().map(Skill::getName).collect(Collectors.toList())).postedByName(job.getPostedBy() != null ? job.getPostedBy().getFullName() : null).postedById(job.getPostedBy() != null ? job.getPostedBy().getId() : null).createdAt(job.getCreatedAt()).updatedAt(job.getUpdatedAt()).build();
    }
    public JobService(final JobRepository jobRepository, final CompanyRepository companyRepository, final UserRepository userRepository, final SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }
}
