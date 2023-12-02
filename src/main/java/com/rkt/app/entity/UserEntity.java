package com.rkt.app.entity;

import jakarta.persistence.*;
import lombok.*;

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
