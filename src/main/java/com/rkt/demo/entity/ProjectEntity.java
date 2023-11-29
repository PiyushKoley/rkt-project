package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rkt.demo.enums.ProjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    private long createdByUser;

    @LastModifiedBy
    @Column(insertable = false)
    private long updatedByUser;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private CustomerEntity customerEntity;
}
