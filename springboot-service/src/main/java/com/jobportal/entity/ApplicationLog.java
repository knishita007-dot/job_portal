package com.jobportal.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "application_logs", indexes = {@Index(name = "idx_applogs_application_id", columnList = "application_id"), @Index(name = "idx_applogs_user_id", columnList = "user_id")})
public class ApplicationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "application_id", nullable = false)
    private Long applicationId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "job_id", nullable = false)
    private Long jobId;
    @Column(nullable = false, length = 50)
    private String action; // e.g., 'applied', 'status_changed', 'viewed', 'withdrawn', 'updated'
    @Column(columnDefinition = "TEXT")
    private String details; // JSON or text representation of details
    @Column(name = "ip_address", length = 45)
    private String ipAddress;
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;
    private static LocalDateTime $default$timestamp() {
        return LocalDateTime.now();
    }
    public static class ApplicationLogBuilder {
        private Long id;
        private Long applicationId;
        private Long userId;
        private Long jobId;
        private String action;
        private String details;
        private String ipAddress;
        private boolean timestamp$set;
        private LocalDateTime timestamp$value;
        ApplicationLogBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder applicationId(final Long applicationId) {
            this.applicationId = applicationId;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder userId(final Long userId) {
            this.userId = userId;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder jobId(final Long jobId) {
            this.jobId = jobId;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder action(final String action) {
            this.action = action;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder details(final String details) {
            this.details = details;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder ipAddress(final String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public ApplicationLog.ApplicationLogBuilder timestamp(final LocalDateTime timestamp) {
            this.timestamp$value = timestamp;
            timestamp$set = true;
            return this;
        }
        public ApplicationLog build() {
            LocalDateTime timestamp$value = this.timestamp$value;
            if (!this.timestamp$set) timestamp$value = ApplicationLog.$default$timestamp();
            return new ApplicationLog(this.id, this.applicationId, this.userId, this.jobId, this.action, this.details, this.ipAddress, timestamp$value);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "ApplicationLog.ApplicationLogBuilder(id=" + this.id + ", applicationId=" + this.applicationId + ", userId=" + this.userId + ", jobId=" + this.jobId + ", action=" + this.action + ", details=" + this.details + ", ipAddress=" + this.ipAddress + ", timestamp$value=" + this.timestamp$value + ")";
        }
    }
    public static ApplicationLog.ApplicationLogBuilder builder() {
        return new ApplicationLog.ApplicationLogBuilder();
    }
    public Long getId() {
        return this.id;
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
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    public void setId(final Long id) {
        this.id = id;
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
    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof ApplicationLog)) return false;
        final ApplicationLog other = (ApplicationLog) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
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
        final java.lang.Object this$timestamp = this.getTimestamp();
        final java.lang.Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null ? other$timestamp != null : !this$timestamp.equals(other$timestamp)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof ApplicationLog;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
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
        final java.lang.Object $timestamp = this.getTimestamp();
        result = result * PRIME + ($timestamp == null ? 43 : $timestamp.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "ApplicationLog(id=" + this.getId() + ", applicationId=" + this.getApplicationId() + ", userId=" + this.getUserId() + ", jobId=" + this.getJobId() + ", action=" + this.getAction() + ", details=" + this.getDetails() + ", ipAddress=" + this.getIpAddress() + ", timestamp=" + this.getTimestamp() + ")";
    }
    public ApplicationLog() {
        this.timestamp = ApplicationLog.$default$timestamp();
    }
    public ApplicationLog(final Long id, final Long applicationId, final Long userId, final Long jobId, final String action, final String details, final String ipAddress, final LocalDateTime timestamp) {
        this.id = id;
        this.applicationId = applicationId;
        this.userId = userId;
        this.jobId = jobId;
        this.action = action;
        this.details = details;
        this.ipAddress = ipAddress;
        this.timestamp = timestamp;
    }
}
