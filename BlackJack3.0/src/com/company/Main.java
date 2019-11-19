package com.company;

public class Main {

    public static void main(String[] args){
        IBlackJack blackJack = new BlackJack();
        blackJack.game();

    }
            /*Card c = new Card(Card.Rank.ACE, Card.Suit.CLUBS);
        Card c2 = new Card(Card.Rank.ACE, Card.Suit.DIAMOND);
        Card c3 = new Card(Card.Rank.ACE, Card.Suit.HEART);
        Map<ICard, Integer> name = new HashMap<>();
        name.put(c, 3);
        name.put(c2, 3);
        name.put(c3, 5);
        name.get(c);
        name.put(c, 4);
        name.remove(c);
        name.put(c, name.get(c) - 1);
        for (Map.Entry<ICard, Integer> e: name.entrySet()){
            e.getKey();
            e.getValue();
        }*/
}
