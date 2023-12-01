package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkt.demo.enums.ProjectType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String projectDescription;
    private String projectManager;
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @CreationTimestamp
    @Column(updatable = false,nullable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedAt;

    @CreatedBy
    @Column(updatable = false,nullable = false)
    private Long createdByUser;

    @LastModifiedBy
    @Column(insertable = false)
    private Long updatedByUser;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private CustomerEntity customerEntity;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "projectEntity")
    @JsonIgnore
    private Set<TaskEntity> taskEntitySet;
}
