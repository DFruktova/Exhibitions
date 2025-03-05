package com.example.exhibitions.service;

import com.example.exhibitions.model.Exhibition;
import com.example.exhibitions.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    @Autowired
    public ExhibitionService(ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    public Optional<Exhibition> getExhibitionById(int id) {
        return exhibitionRepository.findById(id);
    }

    public Exhibition createExhibition(Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    public Exhibition updateExhibition(int id, Exhibition exhibition) {
        return exhibitionRepository.findById(id)
                .map(existingExhibition -> {
                    exhibition.setID_exhibition(id); // Ensure ID is set
                    return exhibitionRepository.save(exhibition);
                })
                .orElse(null);
    }

    public void deleteExhibition(int id) {
        exhibitionRepository.deleteById(id);
    }
}