package com.project.resumebuilder.service;

import com.project.resumebuilder.dto.request.ResumeRequest;
import com.project.resumebuilder.dto.response.*;
import com.project.resumebuilder.entity.*;
import com.project.resumebuilder.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    PortofolioRepository portofolioRepository;

    @Autowired
    SkillsResumeRepository skillsResumeRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Transactional(readOnly = true)
    public ResumeResponse getResumeById(Integer id){
        Resume resume = resumeRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "dari id tersebut tidak ditemukan apapun bossq"));
        return convertResumeToResumeResponse(resume);
    }

    @Transactional(readOnly = true)
    public List<ResumeResponse> getResume(){
        List<Resume> resume = resumeRepository.findAll();
        return resume.stream().map(this::convertResumeToResumeResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ResumePublishResponse getPublicResume(){
        Resume resume = resumeRepository.findResumeByStatus(Status.PUBLIC).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Belum ada yang dipublik bossq"));
        return convertResumeToResumePublishResponse(resume);
    }

    @Transactional
    public String updatePublishStatus(Integer id){
        resumeRepository.findResumeIdByStatus(Status.PUBLIC)
                .ifPresent(resumeId ->  resumeRepository.updateStatusResume(Status.DRAFT, resumeId));

        resumeRepository.updateStatusResume(Status.PUBLIC, id);
        return "Sudah diupdate bossq";
    }


    @Transactional
    public String editResume(ResumeRequest resumeReq, Integer id){
        Optional<Status> status = resumeRepository.findResumeStatusById(id);

        if(status.isPresent()){
            educationRepository.deleteByResumeId(id);
            experienceRepository.deleteByResumeId(id);
            portofolioRepository.deleteByResumeId(id);
            skillsResumeRepository.deleteByResumeId(id);

            Resume resume = convertResumeRequestToResume(resumeReq);
            resume.setId(id);
            resume.setStatus(status.get());

            resumeRepository.save(resume);
            return "Sudah di ubah bossq, yakali ga kuyy";

        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id resume ga ada, yakali mau update??");
        }

    }

    @Transactional
    public String addResume(ResumeRequest resumeReq){
        Resume resume = convertResumeRequestToResume(resumeReq);
        resumeRepository.save(resume);
        return "Sudah ditambahkan bossq";
    }

    private ResumeResponse convertResumeToResumeResponse(Resume resume){
        return ResumeResponse.builder()
                .id(resume.getId())
                .design(resume.getDesign())
                .name(resume.getName())
                .photo(resume.getPhoto())
                .description(resume.getDescription())
                .address(resume.getAddress())
                .contact(
                        ContactResponse.builder()
                                .email(resume.getEmail())
                                .portofolioWeb(resume.getPortofolioWeb())
                                .linkedin(resume.getLinkedin())
                                .phone(resume.getPhone())
                                .build()
                )
                .experience(resume.getExperience().stream().map(experience->
                        ExperienceResponse.builder()
                                .title(experience.getTitle())
                                .company(experience.getCompany())
                                .description(experience.getDescription())
                                .startDate(experience.getStartDate())
                                .endDate(experience.getEndDate())
                                .link(experience.getLink())
                                .build()
                        ).collect(Collectors.toList()))
                .education(resume.getEducation().stream().map(education->
                        EducationResponse.builder()
                                .title(education.getTitle())
                                .school(education.getSchool())
                                .description(education.getDescription())
                                .startDate(education.getStartDate())
                                .endDate(education.getEndDate())
                                .build()
                ).collect(Collectors.toList()))
                .portofolio(resume.getPortofolio().stream().map(portofolio->
                        PortofolioResponse.builder()
                                .title(portofolio.getTitle())
                                .role(portofolio.getRole())
                                .description(portofolio.getDescription())
                                .startDate(portofolio.getStartDate())
                                .endDate(portofolio.getEndDate())
                                .link(portofolio.getLink())
                                .build()
                ).collect(Collectors.toList()))
                .skills(resume.getSkillsResume().stream().map(skillResume->
                        SkillsResponse.builder()
                                .idSkill(skillResume.getSkills().getId())
                                .name(skillResume.getSkills().getName())
                                .image(skillResume.getSkills().getImage())
                                .build()
                ).collect(Collectors.toList()))
                .status(resume.getStatus())
                .build();
    }

    private ResumePublishResponse convertResumeToResumePublishResponse(Resume resume){
        return ResumePublishResponse.builder()
                .id(resume.getId())
                .design(resume.getDesign())
                .name(resume.getName())
                .photo(resume.getPhoto())
                .description(resume.getDescription())
                .address(resume.getAddress())
                .contact(
                        ContactResponse.builder()
                                .email(resume.getEmail())
                                .portofolioWeb(resume.getPortofolioWeb())
                                .linkedin(resume.getLinkedin())
                                .phone(resume.getPhone())
                                .build()
                )
                .experience(resume.getExperience().stream().map(experience->
                        ExperienceResponse.builder()
                                .title(experience.getTitle())
                                .company(experience.getCompany())
                                .description(experience.getDescription())
                                .startDate(experience.getStartDate())
                                .endDate(experience.getEndDate())
                                .link(experience.getLink())
                                .build()
                ).collect(Collectors.toList()))
                .education(resume.getEducation().stream().map(education->
                        EducationResponse.builder()
                                .title(education.getTitle())
                                .school(education.getSchool())
                                .description(education.getDescription())
                                .startDate(education.getStartDate())
                                .endDate(education.getEndDate())
                                .build()
                ).collect(Collectors.toList()))
                .portofolio(resume.getPortofolio().stream().map(portofolio->
                        PortofolioResponse.builder()
                                .title(portofolio.getTitle())
                                .role(portofolio.getRole())
                                .description(portofolio.getDescription())
                                .startDate(portofolio.getStartDate())
                                .endDate(portofolio.getEndDate())
                                .link(portofolio.getLink())
                                .build()
                ).collect(Collectors.toList()))
                .skills(resume.getSkillsResume().stream().map(skillResume->
                        SkillsResponse.builder()
                                .idSkill(skillResume.getSkills().getId())
                                .name(skillResume.getSkills().getName())
                                .image(skillResume.getSkills().getImage())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }


    private Resume convertResumeRequestToResume(ResumeRequest resumeReq){
        Resume resume = Resume.builder()
                .design(resumeReq.getDesign())
                .name(resumeReq.getName())
                .photo(resumeReq.getPhoto())
                .description(resumeReq.getDescription())
                .address(resumeReq.getAddress())
                .email(resumeReq.getContact().getEmail())
                .portofolioWeb(resumeReq.getContact().getPortofolioWeb())
                .linkedin(resumeReq.getContact().getLinkedin())
                .phone(resumeReq.getContact().getPhone())
                .status((Status.DRAFT))
                .build();

        resume.setSkillsResume(resumeReq.getSkills().stream().map(skills ->
                SkillsResume.builder()
                        .skills(skillsRepository.findById(skills.getId()).orElseThrow(()->
                                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Skill nya ga ada cok, cek lagi daftar skill")))
                        .resume(resume)
                        .build()
        ).collect(Collectors.toList()));

        resume.setExperience(resumeReq.getExperience().stream().map(experience->
                Experience.builder()
                        .title(experience.getTitle())
                        .company(experience.getCompany())
                        .description(experience.getDescription())
                        .startDate(experience.getStartDate())
                        .endDate(experience.getEndDate())
                        .link(experience.getLink())
                        .resume(resume)
                        .build()
        ).collect(Collectors.toList()));

        resume.setEducation(resumeReq.getEducation().stream().map(education->
                Education.builder()
                        .title(education.getTitle())
                        .school(education.getSchool())
                        .description(education.getDescription())
                        .startDate(education.getStartDate())
                        .endDate(education.getEndDate())
                        .resume(resume)
                        .build()
        ).collect(Collectors.toList()));

        resume.setPortofolio(resumeReq.getPortofolio().stream().map(portofolio->
                Portofolio.builder()
                        .title(portofolio.getTitle())
                        .role(portofolio.getRole())
                        .description(portofolio.getDescription())
                        .startDate(portofolio.getStartDate())
                        .endDate(portofolio.getEndDate())
                        .link(portofolio.getLink())
                        .resume(resume)
                        .build()
        ).collect(Collectors.toList()));

        return resume;
    }
}
