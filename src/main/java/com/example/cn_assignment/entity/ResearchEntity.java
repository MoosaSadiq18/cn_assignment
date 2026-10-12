package com.example.cn_assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

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

    @NotBlank(message = "Research title is required")
    private String researchTitle;

    @NotBlank(message = "Description is required")
    private String researchDescription;

    @NotBlank(message = "Research area is required")
    private String researchArea;

    @NotBlank(message = "Faculty name is required")
    private String facultyName;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Required skills are required")
    private String requiredSkills;

    @NotNull(message = "Available positions is required")
    @Min(value = 1, message = "At least one position is required")
    private Integer availablePositions;

    @NotNull(message = "Application deadline is required")
    @Future(message = "Application deadline must be in the future")
    private LocalDate applicationDeadline;

    @Enumerated(EnumType.STRING)
    private OpportunityStatus status;
}
