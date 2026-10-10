package com.example.cn_assignment.service;

import com.example.cn_assignment.entity.OpportunityStatus;
import com.example.cn_assignment.entity.ResearchEntity;
import com.example.cn_assignment.repository.OpportunityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService {

    private final OpportunityRepository repository;

    public OpportunityService(OpportunityRepository repository){
        this.repository = repository;
    }

    public ResearchEntity create(ResearchEntity opportunity){
        if (opportunity.getStatus() == null) {
            opportunity.setStatus(OpportunityStatus.OPEN);
        }

        return repository.save(opportunity);
    }

    public List<ResearchEntity> getAll(){
        return repository.findAll();
    }

    public ResearchEntity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Research not found"));
    }

    public ResearchEntity update(Long id, ResearchEntity updatedOpportunity){

        ResearchEntity existing = repository.getById(id);

        existing.setResearchTitle(updatedOpportunity.getResearchTitle());
        existing.setResearchDescription(updatedOpportunity.getResearchDescription());
        existing.setResearchArea(updatedOpportunity.getResearchArea());
        existing.setFacultyName(updatedOpportunity.getFacultyName());
        existing.setDepartment(updatedOpportunity.getDepartment());
        existing.setRequiredSkills(updatedOpportunity.getRequiredSkills());
        existing.setAvailablePositions(updatedOpportunity.getAvailablePositions());
        existing.setApplicationDeadline(updatedOpportunity.getApplicationDeadline());
        existing.setStatus(updatedOpportunity.getStatus());

        return repository.save(existing);
    }

    public boolean delete(Long id){
        ResearchEntity opportunity = repository.getById(id);
        if(opportunity==null){
            return false;
        }
        repository.delete(opportunity);
        return true;
    }
}
