package com.project.resumebuilder.controller;

import com.project.resumebuilder.dto.request.ResumeRequest;
import com.project.resumebuilder.dto.response.ResumePublishResponse;
import com.project.resumebuilder.dto.response.ResumeResponse;
import com.project.resumebuilder.dto.response.WebResponse;
import com.project.resumebuilder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(resumeService.addResume(resumeReq))
                .build());
    }

    @GetMapping (value = "/resume",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<List<ResumeResponse>>> showResume(){
        return ResponseEntity.ok(WebResponse.<List<ResumeResponse>>builder()
                .data(resumeService.getResume())
                .build());
    }

    @GetMapping (value = "/resume/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<ResumeResponse>> showResumeById(@PathVariable Integer id){
        return ResponseEntity.ok(WebResponse.<ResumeResponse>builder()
                .data(resumeService.getResumeById(id))
                .build());
    }

    @PatchMapping (value = "/resume/publish/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> updatePublishStatus(@PathVariable Integer id){
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(resumeService.updatePublishStatus(id))
                .build());
    }


    @GetMapping (value = "/resume/publish",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<ResumePublishResponse>> showPublishResume(){
        return ResponseEntity.ok(WebResponse.<ResumePublishResponse>builder()
                .data(resumeService.getPublicResume())
                .build());
    }


    @PutMapping (value = "/resume/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> updateResume(@RequestBody ResumeRequest resumeReq, @PathVariable Integer id){
        return ResponseEntity.ok(WebResponse.<String>builder()
                .data(resumeService.editResume(resumeReq, id))
                .build());
    }

}
