package ru.gmotrich.reddit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Instant createdDate;
}
