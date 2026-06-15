package com.jobportal.dto;
public class ApplicationLogDTO {
    private Long applicationId;
    private Long userId;
    private Long jobId;
    private String action;
    private String details;
    private String ipAddress;
    public ApplicationLogDTO() {
    }
    public Long getApplicationId() {
        return this.applicationId;
    }
    public Long getUserId() {
        return this.userId;
    }
    public Long getJobId() {
        return this.jobId;
    }
    public String getAction() {
        return this.action;
    }
    public String getDetails() {
        return this.details;
    }
    public String getIpAddress() {
        return this.ipAddress;
    }
    public void setApplicationId(final Long applicationId) {
        this.applicationId = applicationId;
    }
    public void setUserId(final Long userId) {
        this.userId = userId;
    }
    public void setJobId(final Long jobId) {
        this.jobId = jobId;
    }
    public void setAction(final String action) {
        this.action = action;
    }
    public void setDetails(final String details) {
        this.details = details;
    }
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ApplicationLogDTO)) return false;
        final ApplicationLogDTO other = (ApplicationLogDTO) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$applicationId = this.getApplicationId();
        final java.lang.Object other$applicationId = other.getApplicationId();
        if (this$applicationId == null ? other$applicationId != null : !this$applicationId.equals(other$applicationId)) return false;
        final java.lang.Object this$userId = this.getUserId();
        final java.lang.Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final java.lang.Object this$jobId = this.getJobId();
        final java.lang.Object other$jobId = other.getJobId();
        if (this$jobId == null ? other$jobId != null : !this$jobId.equals(other$jobId)) return false;
        final java.lang.Object this$action = this.getAction();
        final java.lang.Object other$action = other.getAction();
        if (this$action == null ? other$action != null : !this$action.equals(other$action)) return false;
        final java.lang.Object this$details = this.getDetails();
        final java.lang.Object other$details = other.getDetails();
        if (this$details == null ? other$details != null : !this$details.equals(other$details)) return false;
        final java.lang.Object this$ipAddress = this.getIpAddress();
        final java.lang.Object other$ipAddress = other.getIpAddress();
        if (this$ipAddress == null ? other$ipAddress != null : !this$ipAddress.equals(other$ipAddress)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ApplicationLogDTO;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $applicationId = this.getApplicationId();
        result = result * PRIME + ($applicationId == null ? 43 : $applicationId.hashCode());
        final java.lang.Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final java.lang.Object $jobId = this.getJobId();
        result = result * PRIME + ($jobId == null ? 43 : $jobId.hashCode());
        final java.lang.Object $action = this.getAction();
        result = result * PRIME + ($action == null ? 43 : $action.hashCode());
        final java.lang.Object $details = this.getDetails();
        result = result * PRIME + ($details == null ? 43 : $details.hashCode());
        final java.lang.Object $ipAddress = this.getIpAddress();
        result = result * PRIME + ($ipAddress == null ? 43 : $ipAddress.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "ApplicationLogDTO(applicationId=" + this.getApplicationId() + ", userId=" + this.getUserId() + ", jobId=" + this.getJobId() + ", action=" + this.getAction() + ", details=" + this.getDetails() + ", ipAddress=" + this.getIpAddress() + ")";
    }
}
