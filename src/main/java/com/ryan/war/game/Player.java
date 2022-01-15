package com.ryan.war.game;

import com.ryan.war.game.Cards.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Player {
    private String playerName;
    private LinkedList<Card> deck;
    private Card currentCard;
    private int numLifetimeWins;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public Card drawCard() {
        currentCard = deck.remove();
        return currentCard;
    }

    public void addCards(List<Card> cards) {
        deck.addAll(cards);
    }

    public String printDeck() {
        StringBuilder string = new StringBuilder();
        this.deck.stream().forEach(card -> {
            string.append(card.toString() + ", ");
        });
        return string.toString();
    }



}
