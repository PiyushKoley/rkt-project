package com.rkt.app.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name="refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    private Instant expiry;
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserEntity userEntity;
}
