package com.jobportal.dto.response;
import java.time.LocalDateTime;
public class ApplicationResponse {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String companyName;
    private Long userId;
    private String userName;
    private String status;
    private String resumeUrl;
    private String coverLetter;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;
    ApplicationResponse(final Long id, final Long jobId, final String jobTitle, final String companyName, final Long userId, final String userName, final String status, final String resumeUrl, final String coverLetter, final LocalDateTime appliedAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.resumeUrl = resumeUrl;
        this.coverLetter = coverLetter;
        this.appliedAt = appliedAt;
        this.updatedAt = updatedAt;
    }
    public static class ApplicationResponseBuilder {
        private Long id;
        private Long jobId;
        private String jobTitle;
        private String companyName;
        private Long userId;
        private String userName;
        private String status;
        private String resumeUrl;
        private String coverLetter;
        private LocalDateTime appliedAt;
        private LocalDateTime updatedAt;
        ApplicationResponseBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder jobId(final Long jobId) {
            this.jobId = jobId;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder jobTitle(final String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder companyName(final String companyName) {
            this.companyName = companyName;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder userName(final String userName) {
            this.userName = userName;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder status(final String status) {
            this.status = status;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder resumeUrl(final String resumeUrl) {
            this.resumeUrl = resumeUrl;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder coverLetter(final String coverLetter) {
            this.coverLetter = coverLetter;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder appliedAt(final LocalDateTime appliedAt) {
            this.appliedAt = appliedAt;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationResponse.ApplicationResponseBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        public ApplicationResponse build() {
            return new ApplicationResponse(this.id, this.jobId, this.jobTitle, this.companyName, this.userId, this.userName, this.status, this.resumeUrl, this.coverLetter, this.appliedAt, this.updatedAt);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "ApplicationResponse.ApplicationResponseBuilder(id=" + this.id + ", jobId=" + this.jobId + ", jobTitle=" + this.jobTitle + ", companyName=" + this.companyName + ", userId=" + this.userId + ", userName=" + this.userName + ", status=" + this.status + ", resumeUrl=" + this.resumeUrl + ", coverLetter=" + this.coverLetter + ", appliedAt=" + this.appliedAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
    public static ApplicationResponse.ApplicationResponseBuilder builder() {
        return new ApplicationResponse.ApplicationResponseBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public Long getJobId() {
        return this.jobId;
    }
    public String getJobTitle() {
        return this.jobTitle;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public Long getUserId() {
        return this.userId;
    }
    public String getUserName() {
        return this.userName;
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
    public void setJobId(final Long jobId) {
        this.jobId = jobId;
    }
    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    public void setUserName(final String userName) {
        this.userName = userName;
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
        if (!(o instanceof ApplicationResponse)) return false;
        final ApplicationResponse other = (ApplicationResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$jobId = this.getJobId();
        final java.lang.Object other$jobId = other.getJobId();
        if (this$jobId == null ? other$jobId != null : !this$jobId.equals(other$jobId)) return false;
        final java.lang.Object this$userId = this.getUserId();
        final java.lang.Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final java.lang.Object this$jobTitle = this.getJobTitle();
        final java.lang.Object other$jobTitle = other.getJobTitle();
        if (this$jobTitle == null ? other$jobTitle != null : !this$jobTitle.equals(other$jobTitle)) return false;
        final java.lang.Object this$companyName = this.getCompanyName();
        final java.lang.Object other$companyName = other.getCompanyName();
        if (this$companyName == null ? other$companyName != null : !this$companyName.equals(other$companyName)) return false;
        final java.lang.Object this$userName = this.getUserName();
        final java.lang.Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
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
        return other instanceof ApplicationResponse;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $jobId = this.getJobId();
        result = result * PRIME + ($jobId == null ? 43 : $jobId.hashCode());
        final java.lang.Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final java.lang.Object $jobTitle = this.getJobTitle();
        result = result * PRIME + ($jobTitle == null ? 43 : $jobTitle.hashCode());
        final java.lang.Object $companyName = this.getCompanyName();
        result = result * PRIME + ($companyName == null ? 43 : $companyName.hashCode());
        final java.lang.Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
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
        return "ApplicationResponse(id=" + this.getId() + ", jobId=" + this.getJobId() + ", jobTitle=" + this.getJobTitle() + ", companyName=" + this.getCompanyName() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", status=" + this.getStatus() + ", resumeUrl=" + this.getResumeUrl() + ", coverLetter=" + this.getCoverLetter() + ", appliedAt=" + this.getAppliedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }
}
