package com.example.exhibitions.service;

import com.example.exhibitions.model.Visitor;
import com.example.exhibitions.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exhibitions.model.Visitor;
import com.example.exhibitions.repository.VisitorRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Optional<Visitor> getVisitorById(int id) {
        return visitorRepository.findById(id);
    }

    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Visitor updateVisitor(int id, Visitor visitor) {
        return visitorRepository.findById(id)
                .map(existingVisitor -> {
                    visitor.setID_visitor(id);
                    return visitorRepository.save(visitor);
                })
                .orElse(null);
    }

    public void deleteVisitor(int id) {
        visitorRepository.deleteById(id);
    }

    public List<Visitor> searchVisitors(String searchTerm) {
        Specification<Visitor> spec = (root, query, criteriaBuilder) -> {
            if (searchTerm == null || searchTerm.isEmpty()) {
                return criteriaBuilder.conjunction(); // Возвращаем пустой предикат, если поисковый запрос пустой
            }

            String searchTermLower = searchTerm.toLowerCase(); // Преобразуем поисковый запрос в нижний регистр

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Name")), "%" + searchTermLower + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Surname")), "%" + searchTermLower + "%"));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Email")), "%" + searchTermLower + "%"));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0])); // Объединяем предикаты с помощью OR
        };

        return visitorRepository.findAll(spec);
    }
}

