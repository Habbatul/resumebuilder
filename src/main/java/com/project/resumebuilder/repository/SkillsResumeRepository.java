package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.SkillsResume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsResumeRepository extends JpaRepository<SkillsResume, Integer> {
    void deleteByResumeId(Integer resumeId);
}
