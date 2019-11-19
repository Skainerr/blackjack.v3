package com.company;

public interface IUserInput{
    public boolean wantNextCard();
    public int getBet(String name);
    public boolean wantBet();
    public boolean wantContinue(String name);
    public boolean wantSplit(String name);
    public int numberOfPlayers();
    public int getInitialBank(String name);
}
