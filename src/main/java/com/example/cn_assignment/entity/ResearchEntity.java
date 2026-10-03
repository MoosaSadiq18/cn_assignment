package com.example.cn_assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "research_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String researchTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String researchDescription;

    @Column(nullable = false)
    private String researchArea;

    @Column(nullable = false)
    private String facultyName;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String requiredSkills;

    private int availablePositions;

    @NotNull
    private LocalDate applicationDeadline;

    @Enumerated(EnumType.STRING)
    private OpportunityStatus status;
}
