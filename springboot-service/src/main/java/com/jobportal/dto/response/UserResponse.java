package com.jobportal.dto.response;
import java.time.LocalDateTime;
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String role;
    private String phone;
    private LocalDateTime createdAt;
    UserResponse(final Long id, final String email, final String fullName, final String role, final String phone, final LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.phone = phone;
        this.createdAt = createdAt;
    }
    public static class UserResponseBuilder {
        private Long id;
        private String email;
        private String fullName;
        private String role;
        private String phone;
        private LocalDateTime createdAt;
        UserResponseBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public UserResponse.UserResponseBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public UserResponse.UserResponseBuilder email(final String email) {
            this.email = email;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public UserResponse.UserResponseBuilder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public UserResponse.UserResponseBuilder role(final String role) {
            this.role = role;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public UserResponse.UserResponseBuilder phone(final String phone) {
            this.phone = phone;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public UserResponse.UserResponseBuilder createdAt(final LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public UserResponse build() {
            return new UserResponse(this.id, this.email, this.fullName, this.role, this.phone, this.createdAt);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "UserResponse.UserResponseBuilder(id=" + this.id + ", email=" + this.email + ", fullName=" + this.fullName + ", role=" + this.role + ", phone=" + this.phone + ", createdAt=" + this.createdAt + ")";
        }
    }
    public static UserResponse.UserResponseBuilder builder() {
        return new UserResponse.UserResponseBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getEmail() {
        return this.email;
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
    public void setId(final Long id) {
        this.id = id;
    }
    public void setEmail(final String email) {
        this.email = email;
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
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof UserResponse)) return false;
        final UserResponse other = (UserResponse) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final java.lang.Object this$email = this.getEmail();
        final java.lang.Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
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
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof UserResponse;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final java.lang.Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final java.lang.Object $fullName = this.getFullName();
        result = result * PRIME + ($fullName == null ? 43 : $fullName.hashCode());
        final java.lang.Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final java.lang.Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final java.lang.Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "UserResponse(id=" + this.getId() + ", email=" + this.getEmail() + ", fullName=" + this.getFullName() + ", role=" + this.getRole() + ", phone=" + this.getPhone() + ", createdAt=" + this.getCreatedAt() + ")";
    }
}
