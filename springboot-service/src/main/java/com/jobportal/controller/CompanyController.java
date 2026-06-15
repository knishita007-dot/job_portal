package com.jobportal.controller;
import com.jobportal.entity.Company;
import com.jobportal.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(company));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return ResponseEntity.ok(companyService.updateCompany(id, company));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }
}
