package com.project.resumebuilder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {
    private String email;
    private String portofolioWeb;
    private String linkedin;
    private String phone;
}
