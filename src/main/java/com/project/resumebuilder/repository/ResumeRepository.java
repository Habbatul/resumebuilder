package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.Resume;
import com.project.resumebuilder.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {

    Optional<Resume> findByIdAndUser_Username(Integer id, String userUsername);

    List<Resume> findResumeByUser_Username(String username);

    @Override
    Optional<Resume> findById(Integer id);

    Optional<Resume> findResumeByStatusAndId(Status status, Integer id);

    @Transactional(readOnly = true)
    @Query("SELECT u.status FROM Resume u WHERE u.id= :id AND u.user.username = :userUsername")
    Optional<Status> findResumeStatusById(@Param("id") Integer id, @Param("userUsername") String userUsername);

    @Transactional(readOnly = true)
    @Query("SELECT u.id FROM Resume u WHERE u.status = :status AND u.user.username = :userUsername")
    Optional<Integer> findResumeIdByStatus(@Param("status") Status status, @Param("userUsername") String userUsername);

    @Transactional
    @Modifying
    @Query("UPDATE Resume u SET u.status = :status WHERE u.id = :id AND u.user.username = :userUsername")
    void updateStatusResume(@Param("status") Status status, @Param("id")Integer id, @Param("userUsername")String userUsername);

    boolean existsResumeById(Integer id);

}
