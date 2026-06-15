package com.jobportal.repository;
import com.jobportal.entity.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
    Optional<JobDescription> findByJobId(Long jobId);
}
