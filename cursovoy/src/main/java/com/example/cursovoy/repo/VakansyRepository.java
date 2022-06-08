package com.example.cursovoy.repo;

import com.example.cursovoy.models.Vakansy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VakansyRepository extends JpaRepository<Vakansy, Long> {
    List<Vakansy>  findVakansiesByNamevakansyIsContaining(String name);
}
