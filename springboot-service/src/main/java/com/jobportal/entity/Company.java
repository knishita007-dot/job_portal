package com.jobportal.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Company name is required")
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    @Column(length = 100)
    private String industry;
    @Column(length = 255)
    private String website;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    private static LocalDateTime $default$createdAt() {
        return LocalDateTime.now();
    }
    public static class CompanyBuilder {
        private Long id;
        private String name;
        private String address;
        private String industry;
        private String website;
        private boolean createdAt$set;
        private LocalDateTime createdAt$value;
        CompanyBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public Company.CompanyBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Company.CompanyBuilder name(final String name) {
            this.name = name;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Company.CompanyBuilder address(final String address) {
            this.address = address;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Company.CompanyBuilder industry(final String industry) {
            this.industry = industry;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Company.CompanyBuilder website(final String website) {
            this.website = website;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Company.CompanyBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt$value = createdAt;
            createdAt$set = true;
            return this;
        }
        public Company build() {
            LocalDateTime createdAt$value = this.createdAt$value;
            if (!this.createdAt$set) createdAt$value = Company.$default$createdAt();
            return new Company(this.id, this.name, this.address, this.industry, this.website, createdAt$value);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "Company.CompanyBuilder(id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", industry=" + this.industry + ", website=" + this.website + ", createdAt$value=" + this.createdAt$value + ")";
        }
    }
    public static Company.CompanyBuilder builder() {
        return new Company.CompanyBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public String getIndustry() {
        return this.industry;
    }
    public String getWebsite() {
        return this.website;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setAddress(final String address) {
        this.address = address;
    }
    public void setIndustry(final String industry) {
        this.industry = industry;
    }
    public void setWebsite(final String website) {
        this.website = website;
    }
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Company)) return false;
        final Company other = (Company) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$name = this.getName();
        final java.lang.Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final java.lang.Object this$address = this.getAddress();
        final java.lang.Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final java.lang.Object this$industry = this.getIndustry();
        final java.lang.Object other$industry = other.getIndustry();
        if (this$industry == null ? other$industry != null : !this$industry.equals(other$industry)) return false;
        final java.lang.Object this$website = this.getWebsite();
        final java.lang.Object other$website = other.getWebsite();
        if (this$website == null ? other$website != null : !this$website.equals(other$website)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Company;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final java.lang.Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final java.lang.Object $industry = this.getIndustry();
        result = result * PRIME + ($industry == null ? 43 : $industry.hashCode());
        final java.lang.Object $website = this.getWebsite();
        result = result * PRIME + ($website == null ? 43 : $website.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "Company(id=" + this.getId() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", industry=" + this.getIndustry() + ", website=" + this.getWebsite() + ", createdAt=" + this.getCreatedAt() + ")";
    }
    public Company() {
        this.createdAt = Company.$default$createdAt();
    }
    public Company(final Long id, final String name, final String address, final String industry, final String website, final LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.industry = industry;
        this.website = website;
        this.createdAt = createdAt;
    }
}
