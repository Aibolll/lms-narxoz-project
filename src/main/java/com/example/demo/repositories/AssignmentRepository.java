package com.example.demo.repositories;

import com.example.demo.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByLessonId(Long lessonId); // <--- Добавляем поиск по ID урока
}