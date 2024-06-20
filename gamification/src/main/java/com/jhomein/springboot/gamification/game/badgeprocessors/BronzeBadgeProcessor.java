package com.jhomein.springboot.gamification.game.badgeprocessors;

import com.jhomein.springboot.gamification.challenge.ChallengeSolvedEvent;
import com.jhomein.springboot.gamification.game.domain.BadgeType;
import com.jhomein.springboot.gamification.game.domain.ScoreCard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BronzeBadgeProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedEvent solved) {
        return currentScore > 50 ? Optional.of(BadgeType.BRONZE) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.BRONZE;
    }
}
