package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    @Column(unique = true)
    private long customerCode;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private Date updatedAt;

    @CreatedBy
    @Column(nullable = false,updatable = false)
    private Long createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Long updatedBy;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customerEntity")
    @JsonIgnore
    private Set<ProjectEntity> projectEntitySet;

}
