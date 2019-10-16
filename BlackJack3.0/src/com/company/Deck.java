package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Deck implements IDeck {
    private List<Card> listOfCards = new ArrayList<>();
    @Override
    public void generate() {
     for(Card.Suit s : Card.Suit.values()){
         for(Card.Rank r : Card.Rank.values()){
             Card card = new Card(r, s);
             listOfCards.add(card);
         }
     }
    }

    @Override
    public void shuffle() {
        Collections.shuffle(listOfCards);
    }

    @Override
    public ICard drawCard() {
        Card topCard = listOfCards.get(0);
        listOfCards.remove(0);
        return topCard;
    }

    @Override
    public int getNumberOfCardsLeft() {
        return listOfCards.size();
    }

    public Deck(){
        generate();
        generate();
        generate();
        generate();
        generate();
        generate();
        shuffle();
    }
}
