package com.ryan.war.game;

import com.ryan.war.game.cards.Card;
import com.ryan.war.game.cards.Dealer;
import com.ryan.war.player.Player;
import com.ryan.war.player.query.PlayerRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class WarGame {
    @Autowired
    private PlayerRepository playerRepository;


    public Player startGame() throws Exception {
        //creates a dealer and populates the deck
        Dealer dealer = new Dealer();

        //shuffles the deck
        dealer.shuffle();

        //Gets Player One and Player Two from the database
        Player playerOne = playerRepository.findByPlayerId("playerOne");
        Player playerTwo = playerRepository.findByPlayerId("playerTwo");

        //deals the shuffled cards to the players
        dealer.dealCards(playerOne, playerTwo);


        //calls the recursive function playRound until one of the
        //players runs out of cards, the function returns false
        // or a maximum of 300 rounds are played.
        int numRounds = 0;
        while(playRound(playerOne, playerTwo, null) && numRounds < 300) {
            numRounds++;

            if (playerOne.getDeck().size() == 0 || playerTwo.getDeck().size() == 0) {
                break;
            }
            System.out.println("Player one deck length" + playerOne.getDeck().size());
            System.out.println("Player two deck length" + playerTwo.getDeck().size());
        }

        //sets the winning player
        Player winner = playerOne.getDeck().size() > playerTwo.getDeck().size() ? playerOne : playerTwo;

        //adds to the winners lifetime wins and updates the database
        winner.addWin();
        playerRepository.save(winner);

        return winner;
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
                //each player draws a facedown card
                Card playerOneFaceDown = playerOne.drawCard();
                Card playerTwoFaceDown = playerTwo.drawCard();

                if(playerOneFaceDown == null || playerTwoFaceDown == null) {
                    return false;
                }

                //adds the face down cards to the played cards
                playedCards.add(playerOneFaceDown);
                playedCards.add(playerTwoFaceDown);
                return playRound(playerOne, playerTwo, playedCards);

            //player one wins the round and gains the cards
            case 1:
                System.out.println("PLAYER ONE WINS ROUND.");
                playerOne.addCards(playedCards);
                break;

            //player two wins the round and gains the cards
            case -1:
                System.out.println("PLAYER TWO WINS ROUND.");
                playerTwo.addCards(playedCards);
                break;
        }
        return true;
    }


}
