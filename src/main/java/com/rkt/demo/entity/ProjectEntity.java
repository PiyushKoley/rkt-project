package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkt.demo.enums.ProjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@Builder
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column(unique = true)
//    private String projectCode;
    private String projectName;
    private String projectDescription;
    private String projectManager;
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private CustomerEntity customerEntity;
}
