import * as React from "react";
import ChallengeApiClient from "../services/ChallengeApiClient";
import LastAttemptsComponent from "./LastAttemptsComponent";
import LeaderBoardComponent from "./LeaderBoardComponent";

class ChallengeComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            a: '',
            b: '',
            userAlias: '',
            message: '',
            guess: 0
        }
        this.handleSubmitResult = this.handleSubmitResult.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        ChallengeApiClient.challenge().then(res => {
            if (res.ok) {
                res.json().then(json => {
                    console.log(json);
                    this.setState({
                        a: json.factorA,
                        b: json.factorB
                    });
                });
            } else {
                this.updateMessage("Can't reach the server");
            }
        });
    }

    handleChange(event) {
        const name = event.target.name;
        this.setState({
            [name]: event.target.value
        });
    }

    handleSubmitResult(event) {
        event.preventDefault();
        ChallengeApiClient.sendGuess(this.state.userAlias, this.state.a, this.state.b, this.state.guess).then(res => {
            if (res.ok) {
                res.json().then(json => {
                    if (json['correct']) {
                        this.updateMessage("Congratulations! Your guess is correct");
                    } else {
                        this.updateMessage("Oops! Your guess " + json['resultAttempt'] + " is wrong, but keep playing!");
                    }
                    console.log(this.state.userAlias);
                    this.updateLastAttempts(this.state.userAlias);
                    // this.refreshChallenge();
                });
            } else {
                this.updateMessage("Error: server error or not available");
            }
        });
    }

    updateMessage(message) {
        this.setState({
            message
        });
    }

    updateLastAttempts(userAlias) {
        ChallengeApiClient.getAttempts(userAlias).then(res => {
            if (res.ok) {
                let lastAttempts = [];
                res.json().then(data => {
                    console.log(data);
                    data.forEach(item => lastAttempts.push(item));
                    this.setState({lastAttempts});
                });
            }
        });
    }

    render() {
        return (
            <div className="display-column">
                <div>
                    <h3>Your new challenge is</h3>
                    <h1>{this.state.a} x {this.state.b}</h1>
                </div>
                <form onSubmit={this.handleSubmitResult}>
                    <label>
                        Your alias:
                        <input type="text" maxLength="12" name="userAlias" value={this.state.userAlias}
                               onChange={this.handleChange}/>
                    </label>
                    <br/>
                    <label>
                        Your guess:
                        <input type="number" min="0" name="guess" value={this.state.guess}
                               onChange={this.handleChange}/>
                    </label>
                    <br/>
                    <input type="submit" value="Submit"/>
                </form>

                <h4>{this.state.message}</h4>
                {this.state?.['lastAttempts']?.length > 0 && (
                    <LastAttemptsComponent lastAttempts={this.state.lastAttempts}/>)}
                <LeaderBoardComponent/>
            </div>

        );
    }
}

export default ChallengeComponent;