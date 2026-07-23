package com.conorryan.jobtracker;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @EntityGraph(attributePaths = {"company", "interviews"})
    List<Application> findByStatus(ApplicationStatus status);

    @EntityGraph(attributePaths = {"company", "interviews"})
    List<Application> findByCompanyId(Long companyId);

    @Override
    @EntityGraph(attributePaths = {"company", "interviews"})
    List<Application> findAll();
}
