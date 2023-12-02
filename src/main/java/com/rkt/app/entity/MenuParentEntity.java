package com.rkt.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "menu_parents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MenuParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String path;
    private String icon;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parentMenu")
//    @JsonIgnore
    private List<MenuChildEntity> childMenuOptionList;
}
