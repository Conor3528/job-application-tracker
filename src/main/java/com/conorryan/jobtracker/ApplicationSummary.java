package com.conorryan.jobtracker;

import java.time.LocalDate;

public record ApplicationSummary(
        Long id,
        String roleTitle,
        ApplicationStatus status,
        LocalDate dateApplied,
        String companyName,
        int interviewCount
) {
    public static ApplicationSummary from(Application application) {
        return new ApplicationSummary(
                application.getId(),
                application.getRoleTitle(),
                application.getStatus(),
                application.getDateApplied(),
                application.getCompany() != null ? application.getCompany().getName() : null,
                application.getInterviews().size()
        );
    }
}