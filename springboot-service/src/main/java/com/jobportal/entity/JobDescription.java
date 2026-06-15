package com.jobportal.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "job_descriptions")
public class JobDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    private Job job;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(columnDefinition = "TEXT")
    private String requirements;
    @Column(columnDefinition = "TEXT")
    private String benefits;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    private static LocalDateTime $default$createdAt() {
        return LocalDateTime.now();
    }
    private static LocalDateTime $default$updatedAt() {
        return LocalDateTime.now();
    }
    public static class JobDescriptionBuilder {
        private Long id;
        private Job job;
        private String description;
        private String requirements;
        private String benefits;
        private boolean createdAt$set;
        private LocalDateTime createdAt$value;
        private boolean updatedAt$set;
        private LocalDateTime updatedAt$value;
        JobDescriptionBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder job(final Job job) {
            this.job = job;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder description(final String description) {
            this.description = description;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder requirements(final String requirements) {
            this.requirements = requirements;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder benefits(final String benefits) {
            this.benefits = benefits;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt$value = createdAt;
            createdAt$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobDescription.JobDescriptionBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt$value = updatedAt;
            updatedAt$set = true;
            return this;
        }
        public JobDescription build() {
            LocalDateTime createdAt$value = this.createdAt$value;
            if (!this.createdAt$set) createdAt$value = JobDescription.$default$createdAt();
            LocalDateTime updatedAt$value = this.updatedAt$value;
            if (!this.updatedAt$set) updatedAt$value = JobDescription.$default$updatedAt();
            return new JobDescription(this.id, this.job, this.description, this.requirements, this.benefits, createdAt$value, updatedAt$value);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "JobDescription.JobDescriptionBuilder(id=" + this.id + ", job=" + this.job + ", description=" + this.description + ", requirements=" + this.requirements + ", benefits=" + this.benefits + ", createdAt$value=" + this.createdAt$value + ", updatedAt$value=" + this.updatedAt$value + ")";
        }
    }
    public static JobDescription.JobDescriptionBuilder builder() {
        return new JobDescription.JobDescriptionBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public Job getJob() {
        return this.job;
    }
    public String getDescription() {
        return this.description;
    }
    public String getRequirements() {
        return this.requirements;
    }
    public String getBenefits() {
        return this.benefits;
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
    public void setJob(final Job job) {
        this.job = job;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setRequirements(final String requirements) {
        this.requirements = requirements;
    }
    public void setBenefits(final String benefits) {
        this.benefits = benefits;
    }
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof JobDescription)) return false;
        final JobDescription other = (JobDescription) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$job = this.getJob();
        final java.lang.Object other$job = other.getJob();
        if (this$job == null ? other$job != null : !this$job.equals(other$job)) return false;
        final java.lang.Object this$description = this.getDescription();
        final java.lang.Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final java.lang.Object this$requirements = this.getRequirements();
        final java.lang.Object other$requirements = other.getRequirements();
        if (this$requirements == null ? other$requirements != null : !this$requirements.equals(other$requirements)) return false;
        final java.lang.Object this$benefits = this.getBenefits();
        final java.lang.Object other$benefits = other.getBenefits();
        if (this$benefits == null ? other$benefits != null : !this$benefits.equals(other$benefits)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final java.lang.Object this$updatedAt = this.getUpdatedAt();
        final java.lang.Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof JobDescription;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $job = this.getJob();
        result = result * PRIME + ($job == null ? 43 : $job.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $requirements = this.getRequirements();
        result = result * PRIME + ($requirements == null ? 43 : $requirements.hashCode());
        final java.lang.Object $benefits = this.getBenefits();
        result = result * PRIME + ($benefits == null ? 43 : $benefits.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final java.lang.Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "JobDescription(id=" + this.getId() + ", job=" + this.getJob() + ", description=" + this.getDescription() + ", requirements=" + this.getRequirements() + ", benefits=" + this.getBenefits() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }
    public JobDescription() {
        this.createdAt = JobDescription.$default$createdAt();
        this.updatedAt = JobDescription.$default$updatedAt();
    }
    public JobDescription(final Long id, final Job job, final String description, final String requirements, final String benefits, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.job = job;
        this.description = description;
        this.requirements = requirements;
        this.benefits = benefits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
