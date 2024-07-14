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
public class PortofolioResponse {
    private String title;
    private String role;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String link;
}
