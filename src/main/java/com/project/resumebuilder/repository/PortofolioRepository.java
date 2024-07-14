package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.Portofolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortofolioRepository extends JpaRepository<Portofolio, Integer> {
    void deleteByResumeId(Integer resumeId);
}
