package com.project.resumebuilder.controller;

import com.project.resumebuilder.dto.request.ResumeRequest;
import com.project.resumebuilder.dto.response.ResumePublishResponse;
import com.project.resumebuilder.dto.response.ResumeResponse;
import com.project.resumebuilder.dto.response.WebResponse;
import com.project.resumebuilder.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @PostMapping (
            value = "/resume",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WebResponse<String>> addResume(@RequestBody ResumeRequest resumeReq){
        String userUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(resumeService.addResume(resumeReq, userUsername))
                .build());
    }

    @GetMapping (value = "/resume",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<List<ResumeResponse>>> showResume(){
        String userUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(WebResponse.<List<ResumeResponse>>builder()
                .data(resumeService.getResume(userUsername))
                .build());
    }

    @GetMapping (value = "/resume/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<ResumeResponse>> showResumeById(@PathVariable Integer id){
        String userUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(WebResponse.<ResumeResponse>builder()
                .data(resumeService.getResumeById(id, userUsername))
                .build());
    }

    @PatchMapping (value = "/resume/publish/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> updatePublishStatus(@PathVariable Integer id){
        String userUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(resumeService.updatePublishStatus(id, userUsername))
                .build());
    }


    @GetMapping (value = "/resume/publish/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<ResumePublishResponse>> showPublishResume(@PathVariable Integer id){
        return ResponseEntity.ok(WebResponse.<ResumePublishResponse>builder()
                .data(resumeService.getPublicResume(id))
                .build());
    }


    @PutMapping (value = "/resume/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> updateResume(@RequestBody ResumeRequest resumeReq, @PathVariable Integer id){
        String userUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(resumeService.editResume(resumeReq, id, userUsername))
                .build());
    }

}
