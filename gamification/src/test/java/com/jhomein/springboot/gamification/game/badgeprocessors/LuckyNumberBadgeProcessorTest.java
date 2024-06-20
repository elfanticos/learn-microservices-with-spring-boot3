package com.jhomein.springboot.gamification.game.badgeprocessors;

import com.jhomein.springboot.gamification.challenge.ChallengeSolvedEvent;
import com.jhomein.springboot.gamification.game.domain.BadgeType;
import com.jhomein.springboot.gamification.game.domain.ScoreCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LuckyNumberBadgeProcessorTest {
    private LuckyNumberBadgeProcessor badgeProcessor;

    @BeforeEach
    public void setUp() {
        badgeProcessor = new LuckyNumberBadgeProcessor();
    }

    @Test
    public void shouldGiveBadgeIfLuckyFactor() {
        Optional<BadgeType> badgeType = badgeProcessor
                .processForOptionalBadge(10,
                        List.of(new ScoreCard(1L, 1L)),
                        new ChallengeSolvedEvent(1L, true, 42, 10, 1L, "John"));

        assertThat(badgeType).contains(BadgeType.LUCKY_NUMBER);
    }

    @Test
    public void shouldNotGiveBadgeIfNotLuckyFactor() {
        Optional<BadgeType> badgeType = badgeProcessor
                .processForOptionalBadge(10,
                        List.of(new ScoreCard(1L, 1L)),
                        new ChallengeSolvedEvent(1L, true, 43, 10, 1L, "John"));

        assertThat(badgeType).isEmpty();
    }
}
