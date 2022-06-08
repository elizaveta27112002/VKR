package com.example.cursovoy.repo;

import com.example.cursovoy.models.Blog;
import com.example.cursovoy.models.Vakansy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
