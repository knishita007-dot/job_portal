package com.jobportal.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Skill name is required")
    @Column(unique = true, nullable = false, length = 100)
    private String name;
    @ManyToMany(mappedBy = "skills", fetch = FetchType.LAZY)
    private Set<Job> jobs;
    private static Set<Job> $default$jobs() {
        return new HashSet<>();
    }
    public static class SkillBuilder {
        private Long id;
        private String name;
        private boolean jobs$set;
        private Set<Job> jobs$value;
        SkillBuilder() {
        }
        /**
         * @return {@code this}.
         */
        public Skill.SkillBuilder id(final Long id) {
            this.id = id;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Skill.SkillBuilder name(final String name) {
            this.name = name;
            return this;
        }
        /**
         * @return {@code this}.
         */
        public Skill.SkillBuilder jobs(final Set<Job> jobs) {
            this.jobs$value = jobs;
            jobs$set = true;
            return this;
        }
        public Skill build() {
            Set<Job> jobs$value = this.jobs$value;
            if (!this.jobs$set) jobs$value = Skill.$default$jobs();
            return new Skill(this.id, this.name, jobs$value);
        }
        @java.lang.Override
        public java.lang.String toString() {
            return "Skill.SkillBuilder(id=" + this.id + ", name=" + this.name + ", jobs$value=" + this.jobs$value + ")";
        }
    }
    public static Skill.SkillBuilder builder() {
        return new Skill.SkillBuilder();
    }
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Set<Job> getJobs() {
        return this.jobs;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setJobs(final Set<Job> jobs) {
        this.jobs = jobs;
    }
    @java.lang.Override
    public java.lang.String toString() {
        return "Skill(id=" + this.getId() + ", name=" + this.getName() + ", jobs=" + this.getJobs() + ")";
    }
    public Skill() {
        this.jobs = Skill.$default$jobs();
    }
    public Skill(final Long id, final String name, final Set<Job> jobs) {
        this.id = id;
        this.name = name;
        this.jobs = jobs;
    }
    @java.lang.Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof Skill)) return false;
        final Skill other = (Skill) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$id = this.getId();
        final java.lang.Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof Skill;
    }
    @java.lang.Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
