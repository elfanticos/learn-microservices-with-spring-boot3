class ChallengeApiClient {
    static SERVER_URL = 'http://localhost:9080';
    static GET_CHALLENGE = '/challenges/random';
    static POST_RESULT = '/attempts';
    static GET_ATTEMPTS_BY_ALIAS = '/attempts?userAlias=';
    static GET_USERS_BY_IDS = '/users';

    static challenge() {
        return fetch(ChallengeApiClient.SERVER_URL + ChallengeApiClient.GET_CHALLENGE);
    }

    static sendGuess(userAlias, a, b, guess) {
        return fetch(ChallengeApiClient.SERVER_URL + ChallengeApiClient.POST_RESULT,
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userAlias, factorA: a, factorB: b, guess: guess })
            });
    }

    static getAttempts(userAlias) {
        console.log('Get attempts for ' + userAlias);
        return fetch(ChallengeApiClient.SERVER_URL + ChallengeApiClient.GET_ATTEMPTS_BY_ALIAS + userAlias);
    }

    static getUsers(userIds) {
        console.log('userIds => ', userIds)
        return fetch(ChallengeApiClient.SERVER_URL + ChallengeApiClient.GET_USERS_BY_IDS + '/' + userIds.join(','));
    }
}

export default ChallengeApiClient;