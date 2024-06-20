package com.jhomein.springboot.gamification.game;

import com.jhomein.springboot.gamification.challenge.ChallengeSolvedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attempts")
public class GameController {
    private final GameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void postResult(@RequestBody ChallengeSolvedEvent challengeSolvedEvent) {
        gameService.newAttemptForUser(challengeSolvedEvent);
    }
}
