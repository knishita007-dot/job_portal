package com.jobportal.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
public class JobRequest {
    @NotBlank(message = "Job title is required")
    private String title;
    @NotNull(message = "Company ID is required")
    private Long companyId;
    private String location;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String jobType;
    private String experience;
    @NotNull(message = "Posted by user ID is required")
    private Long postedBy;
    private List<Long> skillIds;
    // These fields are forwarded to Node.js for MongoDB storage
    private String description;
    private List<String> requirements;
    private List<String> benefits;
    public JobRequest() {
    }
    public String getTitle() {
        return this.title;
    }
    public Long getCompanyId() {
        return this.companyId;
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
    public Long getPostedBy() {
        return this.postedBy;
    }
    public List<Long> getSkillIds() {
        return this.skillIds;
    }
    public String getDescription() {
        return this.description;
    }
    public List<String> getRequirements() {
        return this.requirements;
    }
    public List<String> getBenefits() {
        return this.benefits;
    }
    public void setTitle(final String title) {
        this.title = title;
    }
    public void setCompanyId(final Long companyId) {
        this.companyId = companyId;
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
    public void setPostedBy(final Long postedBy) {
        this.postedBy = postedBy;
    }
    public void setSkillIds(final List<Long> skillIds) {
        this.skillIds = skillIds;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public void setRequirements(final List<String> requirements) {
        this.requirements = requirements;
    }
    public void setBenefits(final List<String> benefits) {
        this.benefits = benefits;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof JobRequest)) return false;
        final JobRequest other = (JobRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$companyId = this.getCompanyId();
        final java.lang.Object other$companyId = other.getCompanyId();
        if (this$companyId == null ? other$companyId != null : !this$companyId.equals(other$companyId)) return false;
        final java.lang.Object this$postedBy = this.getPostedBy();
        final java.lang.Object other$postedBy = other.getPostedBy();
        if (this$postedBy == null ? other$postedBy != null : !this$postedBy.equals(other$postedBy)) return false;
        final java.lang.Object this$title = this.getTitle();
        final java.lang.Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final java.lang.Object this$location = this.getLocation();
        final java.lang.Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        final java.lang.Object this$salaryMin = this.getSalaryMin();
        final java.lang.Object other$salaryMin = other.getSalaryMin();
        if (this$salaryMin == null ? other$salaryMin != null : !this$salaryMin.equals(other$salaryMin)) return false;
        final java.lang.Object this$salaryMax = this.getSalaryMax();
        final java.lang.Object other$salaryMax = other.getSalaryMax();
        if (this$salaryMax == null ? other$salaryMax != null : !this$salaryMax.equals(other$salaryMax)) return false;
        final java.lang.Object this$jobType = this.getJobType();
        final java.lang.Object other$jobType = other.getJobType();
        if (this$jobType == null ? other$jobType != null : !this$jobType.equals(other$jobType)) return false;
        final java.lang.Object this$experience = this.getExperience();
        final java.lang.Object other$experience = other.getExperience();
        if (this$experience == null ? other$experience != null : !this$experience.equals(other$experience)) return false;
        final java.lang.Object this$skillIds = this.getSkillIds();
        final java.lang.Object other$skillIds = other.getSkillIds();
        if (this$skillIds == null ? other$skillIds != null : !this$skillIds.equals(other$skillIds)) return false;
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
        return other instanceof JobRequest;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $companyId = this.getCompanyId();
        result = result * PRIME + ($companyId == null ? 43 : $companyId.hashCode());
        final java.lang.Object $postedBy = this.getPostedBy();
        result = result * PRIME + ($postedBy == null ? 43 : $postedBy.hashCode());
        final java.lang.Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final java.lang.Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        final java.lang.Object $salaryMin = this.getSalaryMin();
        result = result * PRIME + ($salaryMin == null ? 43 : $salaryMin.hashCode());
        final java.lang.Object $salaryMax = this.getSalaryMax();
        result = result * PRIME + ($salaryMax == null ? 43 : $salaryMax.hashCode());
        final java.lang.Object $jobType = this.getJobType();
        result = result * PRIME + ($jobType == null ? 43 : $jobType.hashCode());
        final java.lang.Object $experience = this.getExperience();
        result = result * PRIME + ($experience == null ? 43 : $experience.hashCode());
        final java.lang.Object $skillIds = this.getSkillIds();
        result = result * PRIME + ($skillIds == null ? 43 : $skillIds.hashCode());
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
        return "JobRequest(title=" + this.getTitle() + ", companyId=" + this.getCompanyId() + ", location=" + this.getLocation() + ", salaryMin=" + this.getSalaryMin() + ", salaryMax=" + this.getSalaryMax() + ", jobType=" + this.getJobType() + ", experience=" + this.getExperience() + ", postedBy=" + this.getPostedBy() + ", skillIds=" + this.getSkillIds() + ", description=" + this.getDescription() + ", requirements=" + this.getRequirements() + ", benefits=" + this.getBenefits() + ")";
    }
}
