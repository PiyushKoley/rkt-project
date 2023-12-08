package com.rkt.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskTitle;
    private String taskDescription;

    @Column(nullable = false,updatable = false)
    private LocalDate taskDate; // this field we will get from front-end...

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedAt;

    @CreatedBy
    @Column(nullable = false,updatable = false)
    private Long createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Long updatedBy;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private UserEntity assignedUser;
}
