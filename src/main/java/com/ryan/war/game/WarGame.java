package com.ryan.war.game;

import com.ryan.war.game.Cards.Card;
import com.ryan.war.game.Cards.Dealer;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WarGame {
    private GameState gameState = GameState.NOT_STARTED;
    private Player playerOne;
    private Player playerTwo;

    //starts the game
    public void startGame() {
        //sets the game state to IN_PROGRESS
        this.gameState = GameState.IN_PROGRESS;

        //creates a dealer which creates a deck
        Dealer dealer = new Dealer();

        //initiates the two players
        this.playerOne = new Player("Player One");
        this.playerTwo = new Player("Player Two");

        //shuffles the deck and deals them out to the two players
        dealer.shuffle();
        dealer.dealCards(playerOne, playerTwo);
        printStatus();
    }

    public boolean playGame(Player playerOne, Player playerTwo, List<Card> cardsInPlay) {
        cardsInPlay.add(playerOne.drawCard());
        cardsInPlay.add(playerTwo.drawCard());
        int result = playerOne.getCurrentCard().compareTo(playerTwo.getCurrentCard());

        switch (result) {
            //tie initiate war
            case 0:
                cardsInPlay.add(playerOne.drawCard());
                cardsInPlay.add(playerTwo.drawCard());
                cardsInPlay.add(playerOne.drawCard());
                cardsInPlay.add(playerTwo.drawCard());
                return playGame(playerOne, playerTwo, cardsInPlay);

            //player one wins
            case 1:
                playerOne.addCards(cardsInPlay);



            //player two wins
            case -1:
                playerTwo.addCards(cardsInPlay);


        }
        printStatus();


    }



    public void printStatus() {
        System.out.println("PLAYER ONES DECK: " + playerOne.printDeck());
        System.out.println("PLAYER TWOS DECK: " + playerTwo.printDeck());
        System.out.println("Player one deck length" + playerOne.getDeck().size());
        System.out.println("Player two deck length" + playerTwo.getDeck().size());

    }


}
