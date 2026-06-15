package com.jobportal.dto.response;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
public class JobResponse {
    private Long id;
    private String title;
    private String companyName;
    private Long companyId;
    private String location;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private String jobType;
    private String experience;
    private Boolean isActive;
    private List<String> skills;
    private String postedByName;
    private Long postedById;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    JobResponse(final Long id, final String title, final String companyName, final Long companyId, final String location, final BigDecimal salaryMin, final BigDecimal salaryMax, final String jobType, final String experience, final Boolean isActive, final List<String> skills, final String postedByName, final Long postedById, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.companyId = companyId;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.jobType = jobType;
        this.experience = experience;
        this.isActive = isActive;
        this.skills = skills;
        this.postedByName = postedByName;
        this.postedById = postedById;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public static class JobResponseBuilder {
        private Long id;
        private String title;
        private String companyName;
        private Long companyId;
        private String location;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private String jobType;
        private String experience;
        private Boolean isActive;
        private List<String> skills;
        private String postedByName;
        private Long postedById;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        JobResponseBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder title(final String title) {
            this.title = title;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder companyName(final String companyName) {
            this.companyName = companyName;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder companyId(final Long companyId) {
            this.companyId = companyId;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder location(final String location) {
            this.location = location;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder salaryMin(final BigDecimal salaryMin) {
            this.salaryMin = salaryMin;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder salaryMax(final BigDecimal salaryMax) {
            this.salaryMax = salaryMax;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder jobType(final String jobType) {
            this.jobType = jobType;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder experience(final String experience) {
            this.experience = experience;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder isActive(final Boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder skills(final List<String> skills) {
            this.skills = skills;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder postedByName(final String postedByName) {
            this.postedByName = postedByName;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder postedById(final Long postedById) {
            this.postedById = postedById;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public JobResponse.JobResponseBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        public JobResponse build() {
            return new JobResponse(this.id, this.title, this.companyName, this.companyId, this.location, this.salaryMin, this.salaryMax, this.jobType, this.experience, this.isActive, this.skills, this.postedByName, this.postedById, this.createdAt, this.updatedAt);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "JobResponse.JobResponseBuilder(id=" + this.id + ", title=" + this.title + ", companyName=" + this.companyName + ", companyId=" + this.companyId + ", location=" + this.location + ", salaryMin=" + this.salaryMin + ", salaryMax=" + this.salaryMax + ", jobType=" + this.jobType + ", experience=" + this.experience + ", isActive=" + this.isActive + ", skills=" + this.skills + ", postedByName=" + this.postedByName + ", postedById=" + this.postedById + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
    public static JobResponse.JobResponseBuilder builder() {
        return new JobResponse.JobResponseBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getTitle() {
        return this.title;
    }
    public String getCompanyName() {
        return this.companyName;
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
    public Boolean getIsActive() {
        return this.isActive;
    }
    public List<String> getSkills() {
        return this.skills;
    }
    public String getPostedByName() {
        return this.postedByName;
    }
    public Long getPostedById() {
        return this.postedById;
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
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
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
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }
    public void setSkills(final List<String> skills) {
        this.skills = skills;
    }
    public void setPostedByName(final String postedByName) {
        this.postedByName = postedByName;
    }
    public void setPostedById(final Long postedById) {
        this.postedById = postedById;
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
        if (!(o instanceof JobResponse)) return false;
        final JobResponse other = (JobResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$companyId = this.getCompanyId();
        final java.lang.Object other$companyId = other.getCompanyId();
        if (this$companyId == null ? other$companyId != null : !this$companyId.equals(other$companyId)) return false;
        final java.lang.Object this$isActive = this.getIsActive();
        final java.lang.Object other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !this$isActive.equals(other$isActive)) return false;
        final java.lang.Object this$postedById = this.getPostedById();
        final java.lang.Object other$postedById = other.getPostedById();
        if (this$postedById == null ? other$postedById != null : !this$postedById.equals(other$postedById)) return false;
        final java.lang.Object this$title = this.getTitle();
        final java.lang.Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final java.lang.Object this$companyName = this.getCompanyName();
        final java.lang.Object other$companyName = other.getCompanyName();
        if (this$companyName == null ? other$companyName != null : !this$companyName.equals(other$companyName)) return false;
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
        final java.lang.Object this$skills = this.getSkills();
        final java.lang.Object other$skills = other.getSkills();
        if (this$skills == null ? other$skills != null : !this$skills.equals(other$skills)) return false;
        final java.lang.Object this$postedByName = this.getPostedByName();
        final java.lang.Object other$postedByName = other.getPostedByName();
        if (this$postedByName == null ? other$postedByName != null : !this$postedByName.equals(other$postedByName)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final java.lang.Object this$updatedAt = this.getUpdatedAt();
        final java.lang.Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof JobResponse;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $companyId = this.getCompanyId();
        result = result * PRIME + ($companyId == null ? 43 : $companyId.hashCode());
        final java.lang.Object $isActive = this.getIsActive();
        result = result * PRIME + ($isActive == null ? 43 : $isActive.hashCode());
        final java.lang.Object $postedById = this.getPostedById();
        result = result * PRIME + ($postedById == null ? 43 : $postedById.hashCode());
        final java.lang.Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final java.lang.Object $companyName = this.getCompanyName();
        result = result * PRIME + ($companyName == null ? 43 : $companyName.hashCode());
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
        final java.lang.Object $skills = this.getSkills();
        result = result * PRIME + ($skills == null ? 43 : $skills.hashCode());
        final java.lang.Object $postedByName = this.getPostedByName();
        result = result * PRIME + ($postedByName == null ? 43 : $postedByName.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final java.lang.Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "JobResponse(id=" + this.getId() + ", title=" + this.getTitle() + ", companyName=" + this.getCompanyName() + ", companyId=" + this.getCompanyId() + ", location=" + this.getLocation() + ", salaryMin=" + this.getSalaryMin() + ", salaryMax=" + this.getSalaryMax() + ", jobType=" + this.getJobType() + ", experience=" + this.getExperience() + ", isActive=" + this.getIsActive() + ", skills=" + this.getSkills() + ", postedByName=" + this.getPostedByName() + ", postedById=" + this.getPostedById() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }
}
