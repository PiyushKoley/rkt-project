package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String customerName;
    @Column(unique = true)
    private long customerCode;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customerEntity")
    @JsonIgnore
    private Set<ProjectEntity> projectEntitySet;

}
