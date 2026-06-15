package com.jobportal.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;
    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;
    @Column(length = 20)
    private String role;
    @Column(length = 20)
    private String phone;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY)
    private List<Job> postedJobs;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Application> applications;
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    private static String $default$role() {
        return "applicant";
    }
    private static LocalDateTime $default$createdAt() {
        return LocalDateTime.now();
    }
    private static LocalDateTime $default$updatedAt() {
        return LocalDateTime.now();
    }
    public static class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private String fullName;
        private boolean role$set;
        private String role$value;
        private String phone;
        private boolean createdAt$set;
        private LocalDateTime createdAt$value;
        private boolean updatedAt$set;
        private LocalDateTime updatedAt$value;
        private List<Job> postedJobs;
        private List<Application> applications;
        UserBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder email(final String email) {
            this.email = email;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder password(final String password) {
            this.password = password;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder role(final String role) {
            this.role$value = role;
            role$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt$value = createdAt;
            createdAt$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder updatedAt(final LocalDateTime updatedAt) {
            this.updatedAt$value = updatedAt;
            updatedAt$set = true;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder postedJobs(final List<Job> postedJobs) {
            this.postedJobs = postedJobs;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public User.UserBuilder applications(final List<Application> applications) {
            this.applications = applications;
            return this;
        }
        public User build() {
            String role$value = this.role$value;
            if (!this.role$set) role$value = User.$default$role();
            LocalDateTime createdAt$value = this.createdAt$value;
            if (!this.createdAt$set) createdAt$value = User.$default$createdAt();
            LocalDateTime updatedAt$value = this.updatedAt$value;
            if (!this.updatedAt$set) updatedAt$value = User.$default$updatedAt();
            return new User(this.id, this.email, this.password, this.fullName, role$value, this.phone, createdAt$value, updatedAt$value, this.postedJobs, this.applications);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "User.UserBuilder(id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", fullName=" + this.fullName + ", role$value=" + this.role$value + ", phone=" + this.phone + ", createdAt$value=" + this.createdAt$value + ", updatedAt$value=" + this.updatedAt$value + ", postedJobs=" + this.postedJobs + ", applications=" + this.applications + ")";
        }
    }
    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getFullName() {
        return this.fullName;
    }
    public String getRole() {
        return this.role;
    }
    public String getPhone() {
        return this.phone;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
    public List<Job> getPostedJobs() {
        return this.postedJobs;
    }
    public List<Application> getApplications() {
        return this.applications;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
    public void setRole(final String role) {
        this.role = role;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setPostedJobs(final List<Job> postedJobs) {
        this.postedJobs = postedJobs;
    }
    public void setApplications(final List<Application> applications) {
        this.applications = applications;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$email = this.getEmail();
        final java.lang.Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final java.lang.Object this$password = this.getPassword();
        final java.lang.Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final java.lang.Object this$fullName = this.getFullName();
        final java.lang.Object other$fullName = other.getFullName();
        if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) return false;
        final java.lang.Object this$role = this.getRole();
        final java.lang.Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final java.lang.Object this$phone = this.getPhone();
        final java.lang.Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final java.lang.Object this$createdAt = this.getCreatedAt();
        final java.lang.Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final java.lang.Object this$updatedAt = this.getUpdatedAt();
        final java.lang.Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        final java.lang.Object this$postedJobs = this.getPostedJobs();
        final java.lang.Object other$postedJobs = other.getPostedJobs();
        if (this$postedJobs == null ? other$postedJobs != null : !this$postedJobs.equals(other$postedJobs)) return false;
        final java.lang.Object this$applications = this.getApplications();
        final java.lang.Object other$applications = other.getApplications();
        if (this$applications == null ? other$applications != null : !this$applications.equals(other$applications)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof User;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final java.lang.Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final java.lang.Object $fullName = this.getFullName();
        result = result * PRIME + ($fullName == null ? 43 : $fullName.hashCode());
        final java.lang.Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final java.lang.Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final java.lang.Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        final java.lang.Object $postedJobs = this.getPostedJobs();
        result = result * PRIME + ($postedJobs == null ? 43 : $postedJobs.hashCode());
        final java.lang.Object $applications = this.getApplications();
        result = result * PRIME + ($applications == null ? 43 : $applications.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "User(id=" + this.getId() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", fullName=" + this.getFullName() + ", role=" + this.getRole() + ", phone=" + this.getPhone() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ", postedJobs=" + this.getPostedJobs() + ", applications=" + this.getApplications() + ")";
    }
    public User() {
        this.role = User.$default$role();
        this.createdAt = User.$default$createdAt();
        this.updatedAt = User.$default$updatedAt();
    }
    public User(final Long id, final String email, final String password, final String fullName, final String role, final String phone, final LocalDateTime createdAt, final LocalDateTime updatedAt, final List<Job> postedJobs, final List<Application> applications) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.phone = phone;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.postedJobs = postedJobs;
        this.applications = applications;
    }
}
