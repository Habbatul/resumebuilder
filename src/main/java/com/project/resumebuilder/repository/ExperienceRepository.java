package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
    void deleteByResumeId(Integer resumeId);
}
