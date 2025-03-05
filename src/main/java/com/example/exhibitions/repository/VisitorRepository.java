package com.example.exhibitions.repository;

import com.example.exhibitions.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

@Repository // Указывает, что это репозиторий
public interface VisitorRepository extends JpaRepository<Visitor, Integer>, JpaSpecificationExecutor<Visitor> {
}