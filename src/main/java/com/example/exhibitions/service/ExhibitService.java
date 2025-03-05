package com.example.exhibitions.service;

import com.example.exhibitions.model.Exhibit;
import com.example.exhibitions.repository.ExhibitRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExhibitService {

    private final ExhibitRepository exhibitRepository;

    @Autowired
    public ExhibitService(ExhibitRepository exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }

    public List<Exhibit> getAllExhibits() {
        return exhibitRepository.findAll();
    }

    public Optional<Exhibit> getExhibitById(Integer id) {
        return exhibitRepository.findById(id);
    }

    public Exhibit createExhibit(Exhibit exhibit) {
        return exhibitRepository.save(exhibit);
    }

    public Exhibit updateExhibit(Integer id, Exhibit exhibit) {
        return exhibitRepository.findById(id)
                .map(existingExhibit -> {
                    exhibit.setID_exhibit(id); // гарантирует что ИД задан
                    return exhibitRepository.save(exhibit);
                })
                .orElse(null); // иначе вызывает исключение
    }

    public void deleteExhibit(Integer id) {
        exhibitRepository.deleteById(id);
    }

    public List<Exhibit> filterExhibits(Map<String, String> filters) {
        Specification<Exhibit> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Iterate over the filters map
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String field = entry.getKey();
                String value = entry.getValue();

                switch (field) {
                    case "name":
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Name")), "%" + value.toLowerCase() + "%"));
                        break;
                    case "author":
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Author")), "%" + value.toLowerCase() + "%"));
                        break;
                    case "fabric":
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("Fabric")), "%" + value.toLowerCase() + "%"));
                        break;
                    case "createYear":
                        try {
                            Integer year = Integer.parseInt(value);
                            predicates.add(criteriaBuilder.equal(root.get("Create_year"), year));
                        } catch (NumberFormatException e) {
                            // Handle the case where the value is not a valid integer
                        }
                        break;
                    case "idExhibition":
                        try {
                            Integer idExhibition = Integer.parseInt(value);
                            predicates.add(criteriaBuilder.equal(root.get("ID_exhibition"), idExhibition));
                        } catch (NumberFormatException e) {
                            // Handle the case where the value is not a valid integer
                        }
                        break;

                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return exhibitRepository.findAll(spec);
    }
}