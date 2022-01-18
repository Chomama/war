package com.ryan.war.player;

import com.ryan.war.game.cards.Card;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Player")
public class Player {
    @Id
    private String playerId;
    @Column(name="wins")
    private int wins;
    @Transient
    private LinkedList<Card> deck;
    @Transient
    private Card currentCard;

    public Card drawCard() {
        if(deck.size() == 0) {
            return null;
        }
        currentCard = deck.remove();
        return currentCard;
    }

    public void addCards(List<Card> cards) {
        deck.addAll(cards);
    }

    public void addWin() {
        this.setWins(wins ++);
    }

    public String printDeck() {
        StringBuilder string = new StringBuilder();
        this.deck.stream().forEach(card -> {
            string.append(card.toString() + ", ");
        });
        return string.toString();
    }



}
