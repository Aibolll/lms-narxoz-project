package com.example.demo.repositories;

import com.example.demo.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findAllByAssignmentId(Long assignmentId);

    List<Submission> findAllByStudentId(Long studentId);
}