package com.rkt.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rkt.demo.enums.Role;
import jakarta.persistence.*;
import lombok.*;

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

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "assignedUser")
//    @JsonIgnore
//    private Set<TaskEntity> setOfTaskAssigned;

}
