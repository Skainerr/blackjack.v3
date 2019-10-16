package com.company;

public interface IDeck {
    public void generate();
    public void shuffle();
    public ICard drawCard();
    public int getNumberOfCardsLeft();
}
