package com.jobportal.dto;
public class JobDescriptionDTO {
    private Long jobId;
    private String description;
    private String requirements;
    private String benefits;
    public JobDescriptionDTO() {
    }
    public Long getJobId() {
        return this.jobId;
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
    public void setJobId(final Long jobId) {
        this.jobId = jobId;
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
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof JobDescriptionDTO)) return false;
        final JobDescriptionDTO other = (JobDescriptionDTO) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$jobId = this.getJobId();
        final java.lang.Object other$jobId = other.getJobId();
        if (this$jobId == null ? other$jobId != null : !this$jobId.equals(other$jobId)) return false;
        final java.lang.Object this$description = this.getDescription();
        final java.lang.Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final java.lang.Object this$requirements = this.getRequirements();
        final java.lang.Object other$requirements = other.getRequirements();
        if (this$requirements == null ? other$requirements != null : !this$requirements.equals(other$requirements)) return false;
        final java.lang.Object this$benefits = this.getBenefits();
        final java.lang.Object other$benefits = other.getBenefits();
        if (this$benefits == null ? other$benefits != null : !this$benefits.equals(other$benefits)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof JobDescriptionDTO;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $jobId = this.getJobId();
        result = result * PRIME + ($jobId == null ? 43 : $jobId.hashCode());
        final java.lang.Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final java.lang.Object $requirements = this.getRequirements();
        result = result * PRIME + ($requirements == null ? 43 : $requirements.hashCode());
        final java.lang.Object $benefits = this.getBenefits();
        result = result * PRIME + ($benefits == null ? 43 : $benefits.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "JobDescriptionDTO(jobId=" + this.getJobId() + ", description=" + this.getDescription() + ", requirements=" + this.getRequirements() + ", benefits=" + this.getBenefits() + ")";
    }
}
