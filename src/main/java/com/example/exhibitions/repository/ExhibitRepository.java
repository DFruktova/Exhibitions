package com.example.exhibitions.repository;

import com.example.exhibitions.model.Exhibit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExhibitRepository extends JpaRepository<Exhibit, Integer>, JpaSpecificationExecutor<Exhibit> {
}