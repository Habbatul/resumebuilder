package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Integer> {
    void deleteByResumeId(Integer resumeId);
}
