class ApiClient {
    static SERVE_URL = 'http://localhost:8080';
    static GET_CHALLENGE = '/challenges/random';
    static POST_RESULT = '/attempts';

    static challenge() {
        return fetch(ApiClient.SERVE_URL + ApiClient.GET_CHALLENGE);
    }

    static sendGuess(userAlias, factorA, factorB, guess) {
        return fetch(ApiClient.SERVE_URL + ApiClient.POST_RESULT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userAlias, factorA, factorB, guess })
        });
    }
}

export default ApiClient;