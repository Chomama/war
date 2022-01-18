package com.ryan.war.game;

import com.ryan.war.game.cards.Card;
import com.ryan.war.game.cards.Dealer;
import com.ryan.war.player.Player;
import com.ryan.war.player.PlayerRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class WarGame {
    private GameState gameState = GameState.NOT_STARTED;
    private Player playerOne;
    private Player playerTwo;

    @Autowired
    private PlayerRepository playerRepository;

    //starts the game
    public void startGame(Player playerOne, Player playerTwo) throws Exception {
        //sets the game state to IN_PROGRESS
        this.gameState = GameState.IN_PROGRESS;

        //creates a dealer which creates a deck
        Dealer dealer = new Dealer();

        //shuffles the deck and deals them out to the two players
        dealer.shuffle();
        dealer.dealCards(playerOne, playerTwo);

        //calls the recursive function playRound until one of the players run out of cards or the function returns false
        while(playRound(playerOne, playerTwo, null)) {
            if (playerOne.getDeck().size() == 0 || playerTwo.getDeck().size() == 0) {
                break;
            }
            System.out.println("Player one deck length" + playerOne.getDeck().size());
            System.out.println("Player two deck length" + playerTwo.getDeck().size());
        }
        //sets the winning player
        Player winner = playerOne.getDeck().size() == 0 ? playerTwo : playerOne;
        winner.addWin();
        gameState = GameState.ENDED;
        System.out.println(winner.getPlayerId() + " is the winner!");
    }

    //executes the game logic and runs recursively in case of war
    public boolean playRound(Player playerOne, Player playerTwo, List<Card> playedCards) {
        if(playedCards == null) {
            playedCards = new ArrayList<>();
        }

        //each player draws a card from their deck
        Card playerOnePlayedCard = playerOne.drawCard();
        Card playerTwoPlayedCard = playerTwo.drawCard();

        //if either players has run out of cards end game
        if(playerOnePlayedCard == null || playerTwoPlayedCard == null) {
            return false;
        }
        System.out.println("PLAYER ONE DRAWS: " + playerOne.getCurrentCard());
        System.out.println("PLAYER TWO DRAWS: " + playerTwo.getCurrentCard());

        //adds each players played cards
        playedCards.add(playerOnePlayedCard);
        playedCards.add(playerTwoPlayedCard);
        int result = playerOne.getCurrentCard().compareTo(playerTwo.getCurrentCard());

        switch (result) {
            //tie -> initiate war
            case 0:
                System.out.println("WAR");
                //each player draws another card facedown
                Card playerOneFaceDown = playerOne.drawCard();
                Card playerTwoFaceDown = playerTwo.drawCard();

                if(playerOneFaceDown == null || playerTwoFaceDown == null) {
                    return false;
                }

                //adds the face down cards to the played cards
                playedCards.add(playerOneFaceDown);
                playedCards.add(playerTwoFaceDown);
                //calls itself recursively to play another round with already played cards
                return playRound(playerOne, playerTwo, playedCards);

            //player one wins the round
            case 1:
                System.out.println("PLAYER ONE WINS ROUND.");
                playerOne.addCards(playedCards);
                break;

            //player two wins the round
            case -1:
                System.out.println("PLAYER TWO WINS ROUND.");
                playerTwo.addCards(playedCards);
                break;
        }
        return true;
    }



    public void printStatus() {
        System.out.println("PLAYER ONES DECK: " + playerOne.printDeck());
        System.out.println("PLAYER TWOS DECK: " + playerTwo.printDeck());
        System.out.println("Player one deck length" + playerOne.getDeck().size());
        System.out.println("Player two deck length" + playerTwo.getDeck().size());

    }


}
