package com.conorryan.jobtracker;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final ApplicationRepository applicationRepository;
    private final InterviewRepository interviewRepository;

    public DashboardService(ApplicationRepository applicationRepository,
                            InterviewRepository interviewRepository) {
        this.applicationRepository = applicationRepository;
        this.interviewRepository = interviewRepository;
    }

    public DashboardStats getStats() {
        Map<ApplicationStatus, Long> byStatus = new LinkedHashMap<>();
        for (ApplicationStatus status : ApplicationStatus.values()) {
            byStatus.put(status, applicationRepository.countByStatus(status));
        }

        long totalApplications = applicationRepository.count();
        long totalInterviews = interviewRepository.count();

        return new DashboardStats(totalApplications, totalInterviews, byStatus);
    }
}
