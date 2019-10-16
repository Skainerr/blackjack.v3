package com.company;

import java.util.List;

public interface IPlayer {
    public void changeActiveHand();

    /**
     * Add money into players bank
     * @param money money to add
     */
    public void addToBank(int money);
    public int getBank();
    public void addCard(ICard ICard);
    public boolean wantContinue();
    public void splitHand();
    public boolean wantSplitHand();
    public int bet();
    public boolean wantBet();
    public int getValueOfHand();
    //add getNextCard
    public boolean wannaNextCard();
    public List<ICard> getHand();

}