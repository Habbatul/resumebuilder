package com.project.resumebuilder.service;

import com.project.resumebuilder.dto.response.SkillsResponse;
import com.project.resumebuilder.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    public List<SkillsResponse> findSkillsAvailable(){
        return skillsRepository.findAll().stream().map(skill ->
                        SkillsResponse.builder()
                                .idSkill(skill.getId())
                                .image(skill.getImage())
                                .name(skill.getName())
                                .build()
                ).collect(Collectors.toList());

    }
}
