package com.jobportal.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "jobs", indexes = {@Index(name = "idx_jobs_company", columnList = "company_id"), @Index(name = "idx_jobs_posted_by", columnList = "posted_by"), @Index(name = "idx_jobs_type", columnList = "job_type"), @Index(name = "idx_jobs_active", columnList = "is_active")})
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Job title is required")
    @Column(nullable = false)
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(length = 255)
    private String location;
    @Column(name = "salary_min", precision = 12, scale = 2)
    private BigDecimal salaryMin;
    @Column(name = "salary_max", precision = 12, scale = 2)
    private BigDecimal salaryMax;
    @Column(name = "job_type", length = 30)
    private String jobType;
    @Column(length = 50)
    private String experience;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "posted_by")
    private User postedBy;
    @Column(name = "is_active")
    private Boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private List<Application> applications;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    private static Boolean $default$isActive() {
        return true;
    }
    private static Set<Skill> $default$skills() {
        return new HashSet<>();
    }
    private static LocalDateTime $default$createdAt() {
        return LocalDateTime.now();
    }
    private static LocalDateTime $default$updatedAt() {
        return LocalDateTime.now();
    }
    public static class JobBuilder {
        private Long id;
        private String title;
        private Company company;
        private String location;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private String jobType;
        private String experience;
        private User postedBy;
        private boolean isActive$set;
        private Boolean isActive$value;
        private boolean skills$set;
        private Set<Skill> skills$value;
        private List<Application> applications;
        private boolean createdAt$set;
        private LocalDateTime createdAt$value;
        private boolean updatedAt$set;
        private LocalDateTime updatedAt$value;
        JobBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder title(final String title) {
            this.title = title;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder company(final Company company) {
            this.company = company;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder location(final String location) {
            this.location = location;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder salaryMin(final BigDecimal salaryMin) {
            this.salaryMin = salaryMin;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder salaryMax(final BigDecimal salaryMax) {
            this.salaryMax = salaryMax;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder jobType(final String jobType) {
            this.jobType = jobType;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder experience(final String experience) {
            this.experience = experience;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder postedBy(final User postedBy) {
            this.postedBy = postedBy;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder isActive(final Boolean isActive) {
            this.isActive$value = isActive;
            isActive$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder skills(final Set<Skill> skills) {
            this.skills$value = skills;
            skills$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder applications(final List<Application> applications) {
            this.applications = applications;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt$value = createdAt;
            createdAt$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Job.JobBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt$value = updatedAt;
            updatedAt$set = true;
            return this;
        }
        public Job build() {
            Boolean isActive$value = this.isActive$value;
            if (!this.isActive$set) isActive$value = Job.$default$isActive();
            Set<Skill> skills$value = this.skills$value;
            if (!this.skills$set) skills$value = Job.$default$skills();
            LocalDateTime createdAt$value = this.createdAt$value;
            if (!this.createdAt$set) createdAt$value = Job.$default$createdAt();
            LocalDateTime updatedAt$value = this.updatedAt$value;
            if (!this.updatedAt$set) updatedAt$value = Job.$default$updatedAt();
            return new Job(this.id, this.title, this.company, this.location, this.salaryMin, this.salaryMax, this.jobType, this.experience, this.postedBy, isActive$value, skills$value, this.applications, createdAt$value, updatedAt$value);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "Job.JobBuilder(id=" + this.id + ", title=" + this.title + ", company=" + this.company + ", location=" + this.location + ", salaryMin=" + this.salaryMin + ", salaryMax=" + this.salaryMax + ", jobType=" + this.jobType + ", experience=" + this.experience + ", postedBy=" + this.postedBy + ", isActive$value=" + this.isActive$value + ", skills$value=" + this.skills$value + ", applications=" + this.applications + ", createdAt$value=" + this.createdAt$value + ", updatedAt$value=" + this.updatedAt$value + ")";
        }
    }
    public static Job.JobBuilder builder() {
        return new Job.JobBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public Company getCompany() {
        return this.company;
    }
    public String getLocation() {
        return this.location;
    }
    public BigDecimal getSalaryMin() {
        return this.salaryMin;
    }
    public BigDecimal getSalaryMax() {
        return this.salaryMax;
    }
    public String getJobType() {
        return this.jobType;
    }
    public String getExperience() {
        return this.experience;
    }
    public User getPostedBy() {
        return this.postedBy;
    }
    public Boolean getIsActive() {
        return this.isActive;
    }
    public Set<Skill> getSkills() {
        return this.skills;
    }
    public List<Application> getApplications() {
        return this.applications;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setCompany(final Company company) {
        this.company = company;
    }
    public void setLocation(final String location) {
        this.location = location;
    }
    public void setSalaryMin(final BigDecimal salaryMin) {
        this.salaryMin = salaryMin;
    }
    public void setSalaryMax(final BigDecimal salaryMax) {
        this.salaryMax = salaryMax;
    }
    public void setJobType(final String jobType) {
        this.jobType = jobType;
    }
    public void setExperience(final String experience) {
        this.experience = experience;
    }
    public void setPostedBy(final User postedBy) {
        this.postedBy = postedBy;
    }
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }
    public void setSkills(final Set<Skill> skills) {
        this.skills = skills;
    }
    public void setApplications(final List<Application> applications) {
        this.applications = applications;
    }
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "Job(id=" + this.getId() + ", title=" + this.getTitle() + ", company=" + this.getCompany() + ", location=" + this.getLocation() + ", salaryMin=" + this.getSalaryMin() + ", salaryMax=" + this.getSalaryMax() + ", jobType=" + this.getJobType() + ", experience=" + this.getExperience() + ", postedBy=" + this.getPostedBy() + ", isActive=" + this.getIsActive() + ", skills=" + this.getSkills() + ", applications=" + this.getApplications() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }
    public Job() {
        this.isActive = Job.$default$isActive();
        this.skills = Job.$default$skills();
        this.createdAt = Job.$default$createdAt();
        this.updatedAt = Job.$default$updatedAt();
    }
    public Job(final Long id, final String title, final Company company, final String location, final BigDecimal salaryMin, final BigDecimal salaryMax, final String jobType, final String experience, final User postedBy, final Boolean isActive, final Set<Skill> skills, final List<Application> applications, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.jobType = jobType;
        this.experience = experience;
        this.postedBy = postedBy;
        this.isActive = isActive;
        this.skills = skills;
        this.applications = applications;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Job)) return false;
        final Job other = (Job) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Job;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
