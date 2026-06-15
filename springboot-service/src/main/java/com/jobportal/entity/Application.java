package com.jobportal.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "applications", uniqueConstraints = @UniqueConstraint(columnNames = {"job_id", "user_id"}), indexes = {@Index(name = "idx_applications_user", columnList = "user_id"), @Index(name = "idx_applications_job", columnList = "job_id"), @Index(name = "idx_applications_status", columnList = "status")})
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(length = 30)
    private String status;
    @Column(name = "resume_url", length = 500)
    private String resumeUrl;
    @Column(name = "cover_letter", columnDefinition = "TEXT")
    private String coverLetter;
    @Column(name = "applied_at", updatable = false)
    private LocalDateTime appliedAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    private static String $default$status() {
        return "submitted";
    }
    private static LocalDateTime $default$appliedAt() {
        return LocalDateTime.now();
    }
    private static LocalDateTime $default$updatedAt() {
        return LocalDateTime.now();
    }
    public static class ApplicationBuilder {
        private Long id;
        private Job job;
        private User user;
        private boolean status$set;
        private String status$value;
        private String resumeUrl;
        private String coverLetter;
        private boolean appliedAt$set;
        private LocalDateTime appliedAt$value;
        private boolean updatedAt$set;
        private LocalDateTime updatedAt$value;
        ApplicationBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder job(final Job job) {
            this.job = job;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder user(final User user) {
            this.user = user;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder status(final String status) {
            this.status$value = status;
            status$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder resumeUrl(final String resumeUrl) {
            this.resumeUrl = resumeUrl;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder coverLetter(final String coverLetter) {
            this.coverLetter = coverLetter;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder appliedAt(final LocalDateTime appliedAt) {
            this.appliedAt$value = appliedAt;
            appliedAt$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Application.ApplicationBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt$value = updatedAt;
            updatedAt$set = true;
            return this;
        }
        public Application build() {
            String status$value = this.status$value;
            if (!this.status$set) status$value = Application.$default$status();
            LocalDateTime appliedAt$value = this.appliedAt$value;
            if (!this.appliedAt$set) appliedAt$value = Application.$default$appliedAt();
            LocalDateTime updatedAt$value = this.updatedAt$value;
            if (!this.updatedAt$set) updatedAt$value = Application.$default$updatedAt();
            return new Application(this.id, this.job, this.user, status$value, this.resumeUrl, this.coverLetter, appliedAt$value, updatedAt$value);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "Application.ApplicationBuilder(id=" + this.id + ", job=" + this.job + ", user=" + this.user + ", status$value=" + this.status$value + ", resumeUrl=" + this.resumeUrl + ", coverLetter=" + this.coverLetter + ", appliedAt$value=" + this.appliedAt$value + ", updatedAt$value=" + this.updatedAt$value + ")";
        }
    }
    public static Application.ApplicationBuilder builder() {
        return new Application.ApplicationBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public Job getJob() {
        return this.job;
    }
    public User getUser() {
        return this.user;
    }
    public String getStatus() {
        return this.status;
    }
    public String getResumeUrl() {
        return this.resumeUrl;
    }
    public String getCoverLetter() {
        return this.coverLetter;
    }
    public LocalDateTime getAppliedAt() {
        return this.appliedAt;
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
    public void setUser(final User user) {
        this.user = user;
    }
    public void setStatus(final String status) {
        this.status = status;
    }
    public void setResumeUrl(final String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
    public void setCoverLetter(final String coverLetter) {
        this.coverLetter = coverLetter;
    }
    public void setAppliedAt(final LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Application)) return false;
        final Application other = (Application) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$job = this.getJob();
        final java.lang.Object other$job = other.getJob();
        if (this$job == null ? other$job != null : !this$job.equals(other$job)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        final java.lang.Object this$status = this.getStatus();
        final java.lang.Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final java.lang.Object this$resumeUrl = this.getResumeUrl();
        final java.lang.Object other$resumeUrl = other.getResumeUrl();
        if (this$resumeUrl == null ? other$resumeUrl != null : !this$resumeUrl.equals(other$resumeUrl)) return false;
        final java.lang.Object this$coverLetter = this.getCoverLetter();
        final java.lang.Object other$coverLetter = other.getCoverLetter();
        if (this$coverLetter == null ? other$coverLetter != null : !this$coverLetter.equals(other$coverLetter)) return false;
        final java.lang.Object this$appliedAt = this.getAppliedAt();
        final java.lang.Object other$appliedAt = other.getAppliedAt();
        if (this$appliedAt == null ? other$appliedAt != null : !this$appliedAt.equals(other$appliedAt)) return false;
        final java.lang.Object this$updatedAt = this.getUpdatedAt();
        final java.lang.Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Application;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $job = this.getJob();
        result = result * PRIME + ($job == null ? 43 : $job.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        final java.lang.Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final java.lang.Object $resumeUrl = this.getResumeUrl();
        result = result * PRIME + ($resumeUrl == null ? 43 : $resumeUrl.hashCode());
        final java.lang.Object $coverLetter = this.getCoverLetter();
        result = result * PRIME + ($coverLetter == null ? 43 : $coverLetter.hashCode());
        final java.lang.Object $appliedAt = this.getAppliedAt();
        result = result * PRIME + ($appliedAt == null ? 43 : $appliedAt.hashCode());
        final java.lang.Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "Application(id=" + this.getId() + ", job=" + this.getJob() + ", user=" + this.getUser() + ", status=" + this.getStatus() + ", resumeUrl=" + this.getResumeUrl() + ", coverLetter=" + this.getCoverLetter() + ", appliedAt=" + this.getAppliedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }
    public Application() {
        this.status = Application.$default$status();
        this.appliedAt = Application.$default$appliedAt();
        this.updatedAt = Application.$default$updatedAt();
    }
    public Application(final Long id, final Job job, final User user, final String status, final String resumeUrl, final String coverLetter, final LocalDateTime appliedAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.job = job;
        this.user = user;
        this.status = status;
        this.resumeUrl = resumeUrl;
        this.coverLetter = coverLetter;
        this.appliedAt = appliedAt;
        this.updatedAt = updatedAt;
    }
}
