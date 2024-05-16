package com.jhomein.springboot.multiplication.challenge;

public interface ChallengeGeneratorService {

    /**
     * @return a randomly generated challenge with factors between 11 and 99
     */
    Challenge randomChallenge();
}
