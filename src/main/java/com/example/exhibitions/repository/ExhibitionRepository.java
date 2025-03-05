package com.example.exhibitions.repository;

import com.example.exhibitions.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Указывает, что это репозиторий
public interface ExhibitionRepository extends JpaRepository<Exhibition, Integer> {
}