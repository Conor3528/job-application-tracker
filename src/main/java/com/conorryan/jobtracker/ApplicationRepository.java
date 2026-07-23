package com.conorryan.jobtracker;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @EntityGraph(attributePaths = "company")
    List<Application> findByStatus(ApplicationStatus status);

    @EntityGraph(attributePaths = "company")
    List<Application> findByCompanyId(Long companyId);
                                                            // 2nd quick fix for the N+1 query problem
    @Override
    @EntityGraph(attributePaths = "company")
    List<Application> findAll();
}