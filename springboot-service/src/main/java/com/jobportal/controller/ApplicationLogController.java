package com.jobportal.controller;
import com.jobportal.dto.ApplicationLogDTO;
import com.jobportal.entity.ApplicationLog;
import com.jobportal.repository.ApplicationLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/logs")
public class ApplicationLogController {
    private final ApplicationLogRepository applicationLogRepository;
    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody ApplicationLogDTO dto) {
        ApplicationLog log = new ApplicationLog();
        log.setApplicationId(dto.getApplicationId());
        log.setUserId(dto.getUserId());
        log.setJobId(dto.getJobId());
        log.setAction(dto.getAction());
        log.setDetails(dto.getDetails());
        log.setIpAddress(dto.getIpAddress());
        applicationLogRepository.save(log);
        return ResponseEntity.ok(log);
    }
    @GetMapping("/application/{appId}")
    public ResponseEntity<List<ApplicationLog>> getLogsByApplication(@PathVariable Long appId) {
        return ResponseEntity.ok(applicationLogRepository.findByApplicationId(appId));
    }
    public ApplicationLogController(final ApplicationLogRepository applicationLogRepository) {
        this.applicationLogRepository = applicationLogRepository;
    }
}
