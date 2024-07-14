package com.project.resumebuilder.controller;

import com.project.resumebuilder.dto.response.SkillsResponse;
import com.project.resumebuilder.dto.response.WebResponse;
import com.project.resumebuilder.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillsController {
    @Autowired
    SkillsService skillsService;

    @GetMapping(value = "/skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<List<SkillsResponse>>> showAllSkills(){

        return ResponseEntity.ok(WebResponse.<List<SkillsResponse>>builder()
                .data(skillsService.findSkillsAvailable())
                .build());
    }
}
