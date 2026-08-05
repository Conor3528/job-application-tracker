package com.conorryan.jobtracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;
    private final CompanyRepository companyRepository;

    public ApplicationController(ApplicationRepository applicationRepository,
                                 CompanyRepository companyRepository) {
        this.applicationRepository = applicationRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping
    public List<ApplicationSummary> getAllApplications(
            @RequestParam(required = false) ApplicationStatus status) {
        List<Application> applications = (status != null)
                ? applicationRepository.findByStatus(status)
                : applicationRepository.findAll();

        return applications.stream()
                .map(ApplicationSummary::from)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        return applicationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@Valid @RequestBody Application application) {
        if (application.getCompany() != null && application.getCompany().getId() != null) {
            return companyRepository.findById(application.getCompany().getId())
                    .map(company -> {
                        application.setCompany(company);
                        return ResponseEntity.ok(applicationRepository.save(application));
                    })
                    .orElse(ResponseEntity.badRequest().build());
        }
        return ResponseEntity.ok(applicationRepository.save(application));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        if (!applicationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        applicationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}