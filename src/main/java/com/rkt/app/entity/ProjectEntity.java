package com.rkt.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
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
    private String projectType;

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

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "projectEntitySet")
    @JsonBackReference
    @JsonIgnore
    private Set<UserEntity> assignedUsers;
}
