class GameApiClient {
    static  SERVICE_URL = 'http://localhost:8081';
    static GET_LEADERBOARD = '/leaders';

    static leaderBoard() {
        return fetch(GameApiClient.SERVICE_URL + GameApiClient.GET_LEADERBOARD);
    }
}

export default GameApiClient;