package com.jhomein.springboot.multiplication.challenge;

import com.jhomein.springboot.multiplication.user.User;
import com.jhomein.springboot.multiplication.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final UserRepository userRepository;
    private final ChallengeAttemptRepository challengeAttemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        // check if the user already exists for that alias, otherwise create it
        User user = userRepository.findByAlias(attemptDTO.getUserAlias())
                .orElseGet(() -> {
                    log.info("Creating new user with alias {}", attemptDTO.getUserAlias());
                    return userRepository.save(new User(attemptDTO.getUserAlias()));
                });

        // check if the attempt is correct
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        // Builds the domain object. Null id for now.
        ChallengeAttempt checkedAttempt = new ChallengeAttempt(
                null,
                user,
                attemptDTO.getFactorA(),
                attemptDTO.getFactorB(),
                attemptDTO.getGuess(),
                isCorrect
        );

        // Stores the attempt
        return challengeAttemptRepository.save(checkedAttempt);
    }

    @Override
    public List<ChallengeAttempt> getStatsForUser(String userAlias) {
        return challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
    }
}
