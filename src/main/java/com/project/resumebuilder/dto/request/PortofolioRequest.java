package com.project.resumebuilder.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class PortofolioRequest {
    private String title;
    private String role;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String link;
}
