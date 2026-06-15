package com.jobportal.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class UserRequest {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Full name is required")
    private String fullName;
    private String role;
    private String phone;
    public UserRequest() {
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
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof UserRequest)) return false;
        final UserRequest other = (UserRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
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
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof UserRequest;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
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
        return result;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "UserRequest(email=" + this.getEmail() + ", password=" + this.getPassword() + ", fullName=" + this.getFullName() + ", role=" + this.getRole() + ", phone=" + this.getPhone() + ")";
    }
}
