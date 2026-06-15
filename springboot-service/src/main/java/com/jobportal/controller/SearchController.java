package com.jobportal.controller;
import com.jobportal.entity.Job;
import com.jobportal.repository.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final JobRepository jobRepository;
    @PostMapping
    public ResponseEntity<?> searchJobs(@RequestBody Map<String, Object> payload) {
        String query = (String) payload.get("query");
        if (query == null || query.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Search query is required"));
        }
        Integer topKObj = (Integer) payload.get("topK");
        int topK = topKObj != null ? topKObj : 10;
        List<Job> foundJobs = jobRepository.searchByKeyword(query.trim());
        // Limit results manually
        if (foundJobs.size() > topK) {
            foundJobs = foundJobs.subList(0, topK);
        }
        List<Map<String, Object>> results =  // Dummy score for text search fallback
        foundJobs.stream().map(j -> {
            Map<String, Object> map = new HashMap<>();
            map.put("jobId", j.getId());
            map.put("score", 1.0);
            map.put("matchedText", j.getTitle() + " - " + j.getLocation());
            return map;
        }).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("query", query.trim());
        response.put("total", results.size());
        response.put("results", results);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("status", "ok", "service", "text-search", "model", "postgres-ilike", "dimensions", 0, "similarity", "none"));
    }
    public SearchController(final JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
}
