package com.conorryan.jobtracker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Role title is required")
    private String roleTitle;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDate dateApplied;

    private String source;

    private String salaryRange;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;

    private String postingUrl;

    @ManyToOne(fetch = FetchType.LAZY) // 1st quick fix to the N+1 query problem
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interview> interviews = new ArrayList<>();

    public Application() {
    }

    public Application(String roleTitle, ApplicationStatus status, LocalDate dateApplied, Company company) {
        this.roleTitle = roleTitle;
        this.status = status;
        this.dateApplied = dateApplied;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getPostingUrl() {
        return postingUrl;
    }

    public void setPostingUrl(String postingUrl) {
        this.postingUrl = postingUrl;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}
