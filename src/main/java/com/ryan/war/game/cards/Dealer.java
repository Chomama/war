package com.ryan.war.game.cards;

import com.ryan.war.game.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class Dealer {
    private List<Card> cards = new ArrayList<>();

    public Dealer() {
        populateDeck();
    }

    public void populateDeck() {
        for(Suit s : Suit.values()) {
            for(Rank r : Rank.values()) {
                Card c = new Card(s, r);
                this.cards.add(c);
            }
        }
    }

    public void dealCards(Player playerOne, Player playerTwo) {
        playerOne.setDeck(this.cards.subList(0, 26).stream().collect(Collectors.toCollection(LinkedList::new)));
        playerTwo.setDeck(this.cards.subList(26, 52).stream().collect(Collectors.toCollection(LinkedList::new)));
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        this.cards.stream().forEach(card -> {
            string.append(card.toString() + ", ");
            System.out.println();
        });
        return string.toString();
    }


}
