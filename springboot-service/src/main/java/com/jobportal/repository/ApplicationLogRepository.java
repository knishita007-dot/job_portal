package com.jobportal.repository;
import com.jobportal.entity.ApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long> {
    List<ApplicationLog> findByApplicationId(Long applicationId);
    List<ApplicationLog> findByUserId(Long userId);
    List<ApplicationLog> findByJobId(Long jobId);
}
