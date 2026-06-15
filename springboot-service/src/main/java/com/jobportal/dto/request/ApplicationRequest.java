package com.jobportal.dto.request;
import jakarta.validation.constraints.NotNull;
public class ApplicationRequest {
    @NotNull(message = "Job ID is required")
    private Long jobId;
    @NotNull(message = "User ID is required")
    private Long userId;
    private String resumeUrl;
    private String coverLetter;
    public ApplicationRequest() {
    }
    public Long getJobId() {
        return this.jobId;
    }
    public Long getUserId() {
        return this.userId;
    }
    public String getResumeUrl() {
        return this.resumeUrl;
    }
    public String getCoverLetter() {
        return this.coverLetter;
    }
    public void setJobId(final Long jobId) {
        this.jobId = jobId;
    }
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    public void setResumeUrl(final String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
    public void setCoverLetter(final String coverLetter) {
        this.coverLetter = coverLetter;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ApplicationRequest)) return false;
        final ApplicationRequest other = (ApplicationRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$jobId = this.getJobId();
        final java.lang.Object other$jobId = other.getJobId();
        if (this$jobId == null ? other$jobId != null : !this$jobId.equals(other$jobId)) return false;
        final java.lang.Object this$userId = this.getUserId();
        final java.lang.Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final java.lang.Object this$resumeUrl = this.getResumeUrl();
        final java.lang.Object other$resumeUrl = other.getResumeUrl();
        if (this$resumeUrl == null ? other$resumeUrl != null : !this$resumeUrl.equals(other$resumeUrl)) return false;
        final java.lang.Object this$coverLetter = this.getCoverLetter();
        final java.lang.Object other$coverLetter = other.getCoverLetter();
        if (this$coverLetter == null ? other$coverLetter != null : !this$coverLetter.equals(other$coverLetter)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ApplicationRequest;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $jobId = this.getJobId();
        result = result * PRIME + ($jobId == null ? 43 : $jobId.hashCode());
        final java.lang.Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final java.lang.Object $resumeUrl = this.getResumeUrl();
        result = result * PRIME + ($resumeUrl == null ? 43 : $resumeUrl.hashCode());
        final java.lang.Object $coverLetter = this.getCoverLetter();
        result = result * PRIME + ($coverLetter == null ? 43 : $coverLetter.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "ApplicationRequest(jobId=" + this.getJobId() + ", userId=" + this.getUserId() + ", resumeUrl=" + this.getResumeUrl() + ", coverLetter=" + this.getCoverLetter() + ")";
    }
}
