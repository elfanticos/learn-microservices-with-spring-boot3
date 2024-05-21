package com.jhomein.springboot.multiplication.challenge;

import jakarta.persistence.*;
import lombok.*;
import com.jhomein.springboot.multiplication.user.User;

/**
 * Identifies the attempt from a {@link User} to solve a challenge
 */
@Entity
@Table(name = "challenge_attempts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int factorA;

    private int factorB;

    private int resultAttempt;

    private boolean correct;
}
