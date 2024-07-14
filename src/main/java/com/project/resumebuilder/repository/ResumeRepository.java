package com.project.resumebuilder.repository;

import com.project.resumebuilder.entity.Resume;
import com.project.resumebuilder.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    @Override
    Optional<Resume> findById(Integer id);

    Optional<Resume> findResumeByStatus(Status status);

    @Transactional(readOnly = true)
    @Query("SELECT u.status FROM Resume u WHERE u.id= :id")
    Optional<Status> findResumeStatusById(@Param("id") Integer id);

    @Transactional(readOnly = true)
    @Query("SELECT u.id FROM Resume u WHERE u.status = :status")
    Optional<Integer> findResumeIdByStatus(@Param("status") Status status);

    @Transactional
    @Modifying
    @Query("UPDATE Resume u SET u.status = :status WHERE u.id = :id")
    void updateStatusResume(@Param("status") Status status, @Param("id")Integer id);

    boolean existsResumeById(Integer id);

}
