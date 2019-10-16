package com.company;

public class Card implements ICard {
    public enum Suit {HEART, DIAMOND, SPADES, CLUBS}
    public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
        private Rank rank;
        private Suit suit;
        private int valueOfHand;


        public Card(Rank r, Suit s) {
            rank = r;
            suit = s;
            switch (r) {
                case KING:
                case QUEEN:
                case JACK:
                case TEN:
                    valueOfHand = 10;
                    break;
                case TWO:
                    valueOfHand = 2;
                    break;
                case THREE:
                    valueOfHand = 3;
                    break;
                case FOUR:
                    valueOfHand = 4;
                    break;
                case FIVE:
                    valueOfHand = 5;
                    break;
                case SIX:
                    valueOfHand = 6;
                    break;
                case SEVEN:
                    valueOfHand = 7;
                    break;
                case EIGHT:
                    valueOfHand = 8;
                    break;
                case NINE:
                    valueOfHand = 9;
                    break;
                case ACE:
                    valueOfHand = 11;
                    break;
            }
        }
        public int getValue(){
            return valueOfHand;
        }

        @Override
        public String toString () {
            return rank.toString() + " " + suit.toString()+ "\n";
        }

}
