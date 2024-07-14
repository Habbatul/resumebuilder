package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillsRepository extends JpaRepository<Skills, Integer> {
}
