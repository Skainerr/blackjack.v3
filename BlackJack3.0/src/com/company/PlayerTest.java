package com.company;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void getValueOfHand() {
        Player p = new Player("bla");

        p.addCard(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        p.addCard(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
        p.addCard(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        p.addCard(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
    }
    @Test
    void playerName(){
        Player p1 = new Player("Tom");
        Player p2 = new Player("Jirka");
        BlackJack bl = new BlackJack();

        p1.addCard(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        p2.addCard(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        p1.addCard(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        p2.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));

    }

}