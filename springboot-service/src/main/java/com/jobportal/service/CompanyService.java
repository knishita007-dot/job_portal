package com.jobportal.service;
import com.jobportal.entity.Company;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", id));
    }
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }
    public Company updateCompany(Long id, Company request) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", id));
        if (request.getName() != null) company.setName(request.getName());
        if (request.getAddress() != null) company.setAddress(request.getAddress());
        if (request.getIndustry() != null) company.setIndustry(request.getIndustry());
        if (request.getWebsite() != null) company.setWebsite(request.getWebsite());
        return companyRepository.save(company);
    }
    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company", id);
        }
        companyRepository.deleteById(id);
    }
    public CompanyService(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
