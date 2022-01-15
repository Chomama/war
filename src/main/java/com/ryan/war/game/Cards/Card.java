package com.ryan.war.game.Cards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Card implements Comparable<Card> {
    private Suit suit;
    private Rank rank;

    public Integer getValue() {
        return rank.ordinal() + 2;
    }

    public int compareTo(Card otherCard) {
        return (this.getValue().compareTo(otherCard.getValue()));
    }

    public String toString() {
        return ((rank.ordinal() + 2)) + " of " + suit.toString();
    }

}
