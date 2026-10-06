package com.example.cn_assignment.controller;

import com.example.cn_assignment.entity.ResearchEntity;
import com.example.cn_assignment.service.OpportunityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@CrossOrigin
public class OpportunityController {

    private final OpportunityService service;

    public OpportunityController(OpportunityService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<ResearchEntity> create(@Valid @RequestBody ResearchEntity opportunity){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(opportunity));
    }

    @GetMapping
    public ResponseEntity<List<ResearchEntity>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResearchEntity> getById(@PathVariable Long id){

        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResearchEntity> update(@PathVariable Long id, @Valid @RequestBody ResearchEntity opportunity){

        return ResponseEntity.ok(
                service.update(id, opportunity)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
