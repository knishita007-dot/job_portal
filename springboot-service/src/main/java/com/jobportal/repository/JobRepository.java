package com.jobportal.repository;
import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByIsActiveTrueOrderByCreatedAtDesc();
    List<Job> findByJobTypeAndIsActiveTrue(String jobType);
    List<Job> findByLocationContainingIgnoreCaseAndIsActiveTrue(String location);
    @Query("SELECT j FROM Job j WHERE j.postedBy.id = :userId ORDER BY j.createdAt DESC")
    List<Job> findByPostedByUserId(@Param("userId") Long userId);
    @Query("SELECT j FROM Job j JOIN j.skills s WHERE s.id IN :skillIds AND j.isActive = true")
    List<Job> findBySkillIds(@Param("skillIds") List<Long> skillIds);
    @Query("SELECT j FROM Job j LEFT JOIN JobDescription jd ON jd.job.id = j.id WHERE " +
           "j.isActive = true AND (" +
           "LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(jd.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Job> searchByKeyword(@Param("keyword") String keyword);
}
