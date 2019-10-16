package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getValueOfHand() {
        Player p = new Player();

        p.addCard(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        p.addCard(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
        p.addCard(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        p.addCard(new Card(Card.Rank.TWO, Card.Suit.CLUBS));

        Assertions.assertEquals(4+5+7+2, p.getValueOfHand().get(0));
    }
}