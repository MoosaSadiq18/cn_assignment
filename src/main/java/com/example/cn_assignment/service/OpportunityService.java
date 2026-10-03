package com.example.cn_assignment.service;

import com.example.cn_assignment.entity.OpportunityStatus;
import com.example.cn_assignment.entity.ResearchEntity;
import com.example.cn_assignment.repository.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResearchEntity getById(Long id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Opportunity not found"));
    }

    public ResearchEntity update(Long id, ResearchEntity updatedOpportunity){

        ResearchEntity existing = getById(id);

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

    public void delete(Long id){
        ResearchEntity opportunity = getById(id);
        repository.delete(opportunity);
    }
}
