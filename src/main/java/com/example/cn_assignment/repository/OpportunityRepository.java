package com.example.cn_assignment.repository;

import com.example.cn_assignment.entity.ResearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<ResearchEntity,Long> {
}
