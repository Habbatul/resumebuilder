package com.project.resumebuilder.dto.request;

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
public class ResumeRequest {
    private String design;
    private String name;
    private String photo;
    private String description;
    private String address;
    private ContactRequest contact;
    private List<ExperienceRequest> experience;
    private List<EducationRequest> education;
    private List<PortofolioRequest> portofolio;
    private List<SkillsRequest> skills;
}
