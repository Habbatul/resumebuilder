package com.project.resumebuilder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {
    private String title;
    private String school;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
