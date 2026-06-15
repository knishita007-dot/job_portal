package com.jobportal.repository;
import com.jobportal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUserIdOrderByAppliedAtDesc(Long userId);
    List<Application> findByJobIdOrderByAppliedAtDesc(Long jobId);
    Optional<Application> findByJobIdAndUserId(Long jobId, Long userId);
    boolean existsByJobIdAndUserId(Long jobId, Long userId);
    List<Application> findByStatus(String status);
}
