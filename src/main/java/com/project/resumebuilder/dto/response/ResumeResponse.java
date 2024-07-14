package com.project.resumebuilder.dto.response;

import com.project.resumebuilder.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponse {
    private Integer id;
    private String design;
    private String name;
    private String photo;
    private String description;
    private String address;
    private ContactResponse contact;
    private List<ExperienceResponse> experience;
    private List<EducationResponse> education;
    private List<PortofolioResponse> portofolio;
    private List<SkillsResponse> skills;
    private Status status;
}
