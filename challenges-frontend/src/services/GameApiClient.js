class GameApiClient {
    static SERVER_URL = 'http://localhost:9000';
    static GET_LEADERBOARD = '/leaders';

    static leaderBoard() {
        return fetch(GameApiClient.SERVER_URL + GameApiClient.GET_LEADERBOARD);
    }

}

export default GameApiClient;