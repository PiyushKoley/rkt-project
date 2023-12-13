package com.rkt.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProjectTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String projectType;
}
