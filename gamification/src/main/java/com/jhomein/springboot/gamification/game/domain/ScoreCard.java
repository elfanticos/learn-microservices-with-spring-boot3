package com.jhomein.springboot.gamification.game.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreCard {
    // The default score assigned to this card, if not specified
    public static final int DEFAULT_SCORE = 0;

    @Id
    @GeneratedValue
    private Long cardId;

    private Long userId;

    private Long attemptId;

    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;

    private int score;

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis() ,DEFAULT_SCORE);
    }
}
