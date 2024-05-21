package com.jhomein.springboot.multiplication.user;

import com.jhomein.springboot.multiplication.challenge.ChallengeAttempt;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores information to identify the user
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<ChallengeAttempt> challengeAttempts;

    public User(final String userAlias) {
        this(null, userAlias, null);
    }
}
