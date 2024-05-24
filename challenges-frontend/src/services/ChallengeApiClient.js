class ChallengeApiClient {
    static SERVE_URL = 'http://localhost:8083';
    static GET_CHALLENGE = '/challenges/random';
    static POST_RESULT = '/attempts';
    static GET_ATTEMPTS_BY_ALIAS = '/attempts?alias=';

    static challenge() {
        return fetch(ChallengeApiClient.SERVE_URL + ChallengeApiClient.GET_CHALLENGE);
    }

    static sendGuess(userAlias, factorA, factorB, guess) {
        return fetch(ChallengeApiClient.SERVE_URL + ChallengeApiClient.POST_RESULT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userAlias, factorA, factorB, guess })
        });
    }

    static getAttempts(userAlias) {
        return fetch(ChallengeApiClient.SERVE_URL + ChallengeApiClient.GET_ATTEMPTS_BY_ALIAS + userAlias);
    }
}

export default ChallengeApiClient;