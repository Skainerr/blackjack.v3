package com.company;

import java.util.ArrayList;
import java.util.List;

public class BlackJack implements IBlackJack {
    private List<IPlayer> players = new ArrayList<>();
    private IDeck deck = new Deck();
    private Player player = new Player();
    private Dealer dealer = new Dealer();
    private UserInput userInput = new UserInput();
    private int originalBank;

    @Override
    public List<IPlayer> winner() {
        List<IPlayer> listOfWinners = new ArrayList<>();
        for (IPlayer p : players) {
            if ((p.getValueOfHand() == 21 && dealer.getValueOfHand() != 21)
                    || (p.getValueOfHand() < 21 && dealer.getValueOfHand() < p.getValueOfHand())
                    || (p.getValueOfHand() < 21 && dealer.getValueOfHand() > 21)) {
                listOfWinners.add(player);
                p.changeActiveHand();
                if (player.getValueOfHand() > 0) {
                    if ((p.getValueOfHand() == 21 && dealer.getValueOfHand() != 21)
                            || (p.getValueOfHand() < 21 && dealer.getValueOfHand() < p.getValueOfHand())
                            || (p.getValueOfHand() < 21 && dealer.getValueOfHand() > 21)) {
                        listOfWinners.add(player);
                    }
                }p.changeActiveHand();
            }
        }return new ArrayList<>();
    }
    public void moneyInBank(IPlayer iPlayer){
        originalBank = userInput.getInitialBank();
        player.addToBank(originalBank);
    }

    @Override
    public void setPricePool(IPlayer IPlayer) {
        int pricePool = userInput.getBet() * 2;
        int money = pricePool + player.getBank();
        player.addToBank(money);
    }

    @Override
    public boolean nextRound() {
        return userInput.wantContinue();
    }
    private void addNewCardIfWanted(IPlayer player){
        while (player.getValueOfHand() < 21){
            if(player.wannaNextCard()){
                player.addCard(deck.drawCard());
            } else {
                break;
            }
        }
        /*
        for(Integer handValue : player.getValueOfHand()){
            while(handValue < 21){
                if(input.wantNextCard()){
                    ICard nextCard = deck.drawCard();
                    player.addCard(nextCard);
                    handValue = handValue + nextCard.getValue();
                }
            }
        }*/
    }
    @Override
    public void game() {
        newDeck();
        userInput.numberOfPlayers();
        moneyInBank();
        do{
            player.bet();
            dealer.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
            dealer.addCard(deck.drawCard());
            player.addCard(deck.drawCard());
            showCardsToPlayer();
            if(player.splitHand()){
                player.getSplitBet();
                player.addCard(deck.drawCard());
                while(addNewCardIfWanted(){
                    showCardsToPlayer();
                }
                player.changeActiveHand();
                player.addCard(deck.drawCard());
                while(addNewCardIfWanted()){
                    showCardsToPlayer();
                }
                player.changeActiveHand();
            }
            addNewCardIfWanted();
            while(dealer.wannaNextCard()){
                dealer.addCard();
            }
            System.out.println(dealer.getValueOfHand());
            winner();
            for(IPlayer p : winner()){
                p.addToBank(setPricePool());
            }
            System.out.println("Congrats here are Players who have won: " + winner() + ".");
            System.out.println("Here are your bank accounts: " + player.getBank());

            if(enoughCards()){
            }else newDeck();

            if(enoughMoney()){
            }else break;

        }while (player.wantContinue());

    }
    @Override
    public boolean enoughMoney(IPlayer IPlayer) {
        if(player.getBank() == 0){
            return false;
        }else return true;
    }

    @Override
    public boolean enoughCards(IDeck IDeck) {
        return deck.getNumberOfCardsLeft() < 25;
    }
    private void newDeck(){
        deck = new Deck();
    }

    @Override
    public void showCardsToPlayer(IPlayer iPlayer) {
        for(ICard card : player.getHand()){
            System.out.println(card.toString());
        }
    }
}
