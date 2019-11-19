package com.company;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {
    private int playerBank;
    private int bet;
    private int splitBet;
    private String playerName;
    private UserInput userInput = new UserInput();

    //private List<List<ICard>> hand = new ArrayList<>();
    private List<ICard> handOne = new ArrayList<>();
    private List<ICard> handTwo = new ArrayList<>();
    private int activeHand = 0;

    public Player(String playerName){
        this.playerName = playerName;
    }

    @Override
    public String getName(){
        String getName = playerName;
        return getName;
    }

    @Override
    public void changeActiveHand(){
        if(activeHand == 0){
            activeHand = 1;
        }else {
            activeHand = 0;}
    }

    @Override
    public void eraseHands(){
        handOne.clear();
        handTwo.clear();
    }
    private List<ICard> getActiveHand(){
        if(activeHand == 0){
            return handOne;
        }else{
            return handTwo;
        }
    }
    /**
     * Add money into players bank
     * @param money money to add
     */
    @Override
    public void addToBank(int money) {
        playerBank += money;
    }

    @Override
    public int getBank() {
        return playerBank;
    }

    @Override
    public void addCard(ICard iCard) {
        getActiveHand().add(iCard);
    }

    @Override
    public boolean wantContinue() {
        return userInput.wantContinue(playerName);
    }

    @Override
    public boolean splitHand() {
        if((acesOnHand() == 2 || splittable())
        && playerBank >= bet()){
            if(wantSplitHand()){
                handTwo.add(handOne.remove(0));
            }
        }
        return false;
    }
    private boolean splittable(){
        return handOne.get(0).getValue() == handOne.get(1).getValue() && handOne.get(0).getValue() < 10;
    }
    private int acesOnHand(){
        List<ICard> hand = getActiveHand();
        int aces = 0;
        for (ICard card : hand){
            if (card.getValue() == 11){
                aces++;
            }
        }
        return aces;
    }

    @Override
    public boolean wantSplitHand() {
        return userInput.wantSplit(playerName);
    }

    @Override
    public int bet() {
        bet = userInput.getBet(playerName);
        while(bet > playerBank){
            System.out.println("Sorry you are don't have enough money, please correct your bet");
            bet = userInput.getBet(playerName);
        }
        playerBank = playerBank - bet;
        return bet;
    }

    @Override
    public int getBet(){
        return bet;
    }

    @Override
    public int getSplitBet(){
        splitBet = bet;
        return splitBet;
    }

    @Override
    public boolean wantBet() {
        return userInput.wantBet();
    }

    @Override
    public int getValueOfHand(){
        int valueOfHand = 0;
        int counter = acesOnHand();
        for (ICard cardInHand : getActiveHand()) {
            valueOfHand += cardInHand.getValue();
            if(valueOfHand > 21){
                if(counter > 0){
                    valueOfHand = valueOfHand - 10;
                    counter -= 1;
                }
            }
        }


        return valueOfHand;
    }
    @Override
    public boolean wannaNextCard(){
        return userInput.wantNextCard();
    }

    @Override
    public List<ICard> getHand() {
        return getActiveHand();
    }

    /*
    public List<Integer> getValueOfHand() {

        List<Integer> valueOfHands = new ArrayList<>();
        int valueOfHand = 0;
        for (int i = 0; i < hand.size(); i++) {
            ICard cardInHand = hand.get(i);
            valueOfHand += cardInHand.getValue();
        }
        valueOfHands.add(valueOfHand);
        return valueOfHands;
    }*/

}
