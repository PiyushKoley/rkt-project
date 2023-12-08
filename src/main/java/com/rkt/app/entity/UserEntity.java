package com.rkt.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String email;
    private String password;
//    @Enumerated(EnumType.STRING)
//    private Role role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "assignedUser")
    @JsonIgnore
    private Set<TaskEntity> setOfTask;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_assigned_project",
            joinColumns ={
                @JoinColumn(name = "user_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "project_id",referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    @JsonIgnore
    private Set<ProjectEntity> projectEntitySet;

}
