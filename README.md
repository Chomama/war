# WAR

This project runs the card game war and is written with Java, Spring, and connects to a MySql database deployed on AWS.


## API's

**/start**

The start endpoint starts the game and will return the results.  The game is capped at 300 rounds as to prevent infinite runs.  

**/getPlayerWins**

This endpoint gets an individual player's number of wins and takes the player's id as parameter.
eg) http://localhost:8080/getPlayerWins?playerId=playerOne

**/getAllPlayerWins**

This endpoint returns all of the players and their corresponding number of wins.

## Installation 

```bash
https://github.com/Chomama/war.git
cd war
./gradlew build
```

## Testing

```bash

./gradlew test

```


**Start the application on localhost:8080/**

```
./gradlew run

```

