package com.example.cursovoy.repo;

import com.example.cursovoy.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findResumesByPostIsContaining(String name);

}
