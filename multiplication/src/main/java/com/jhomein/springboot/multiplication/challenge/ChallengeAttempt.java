package com.jhomein.springboot.multiplication.challenge;

import jakarta.persistence.*;
import lombok.*;
import com.jhomein.springboot.multiplication.user.User;

/**
 * Identifies the attempt from a {@link User} to solve a challenge
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    private int factorA;

    private int factorB;

    private int resultAttempt;

    private boolean correct;
}
