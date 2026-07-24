package com.conorryan.jobtracker;

import java.util.Map;

public record DashboardStats(
        long totalApplications,
        long totalInterviews,
        Map<ApplicationStatus, Long> applicationsByStatus
) {
}
