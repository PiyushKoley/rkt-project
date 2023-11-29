package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String taskTitle;
    private String taskStatus;
    private LocalDate taskDeadline;
    private String taskDescription;


    private long assignee;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ProjectEntity projectEntity;
}
