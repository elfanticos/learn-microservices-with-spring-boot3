package com.jhomein.springboot.multiplication.challenge;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChallengeEventPub {
    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final String challengesTopicExchange;

    public ChallengeEventPub(final AmqpTemplate amqpTemplate, RabbitTemplate rabbitTemplate, @Value("${amqp.exchange.attempts}") final String challengesTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.challengesTopicExchange = challengesTopicExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void challengeSolved(final ChallengeAttempt challengeAttempt) {
        ChallengeSolvedEvent event = buildEvent(challengeAttempt);
        // Routing Key is 'attempt.correct' or 'attempt.wrong'
        String routingKey = "attempt." + (event.isCorrect() ? "correct" : "wrong");
        amqpTemplate.convertAndSend(challengesTopicExchange, routingKey, event);
    }

    public ChallengeSolvedEvent buildEvent(final ChallengeAttempt challengeAttempt) {
        return new ChallengeSolvedEvent(
                challengeAttempt.getId(),
                challengeAttempt.isCorrect(),
                challengeAttempt.getFactorA(),
                challengeAttempt.getFactorB(),
                challengeAttempt.getUser().getId(),
                challengeAttempt.getUser().getAlias()
        );
    }
}
