//package com.rkt.app.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "menu_child")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Builder
//public class MenuChildEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    private String title;
//    private String path;
//    private String icon;
//
//    @ManyToOne
//    @JoinColumn
//    @JsonIgnore
//    private MenuParentEntity parentMenu;
//}
