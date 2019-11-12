package com.company;

import java.util.ArrayList;
import java.util.List;

public class BlackJack implements IBlackJack {
    private List<IPlayer> players = new ArrayList<>();
    private IDeck deck = new Deck();
    private Dealer dealer = new Dealer();
    private UserInput userInput = new UserInput();
    private int numberOfPlayers = userInput.numberOfPlayers();
    private List<IPlayer> playersWhoWantContinue = new ArrayList<>();


    @Override
    public List<IPlayer> winner() {
        List<IPlayer> listOfWinners = new ArrayList<>();
        for (IPlayer p : players) {
            if ((p.getValueOfHand() == 21 && dealer.getValueOfHand() != 21)
                    || (p.getValueOfHand() < 21 && dealer.getValueOfHand() < p.getValueOfHand())
                    || (p.getValueOfHand() < 21 && dealer.getValueOfHand() > 21)) {
                listOfWinners.add(p);

                p.changeActiveHand();

                if (p.getValueOfHand() > 0) {
                    if ((p.getValueOfHand() == 21 && dealer.getValueOfHand() != 21)
                            || (p.getValueOfHand() < 21 && dealer.getValueOfHand() < p.getValueOfHand())
                            || (p.getValueOfHand() < 21 && dealer.getValueOfHand() > 21)) {
                        listOfWinners.add(p);
                    }
                }

                p.changeActiveHand();
            }
        }
        return listOfWinners;
    }
    public void moneyInBank(IPlayer player){
        int originalBank = userInput.getInitialBank();
        player.addToBank(originalBank);
    }

    @Override
    public void setPricePool(IPlayer player) {
        int money = player.getBet() * 2;
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
                showCardsToPlayer(player);
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

        for(int i = 0; i < numberOfPlayers; i++){
            IPlayer player = new Player();
            players.add(player);
        }
        for(IPlayer player : players){
            moneyInBank(player);
        }

        do{
            for(IPlayer player : players) {
                player.getBet();
            }
            for(IPlayer player : players) {
                player.addCard(deck.drawCard());
            }
            dealer.addCard(deck.drawCard());
            for(IPlayer player : players) {
                player.addCard(deck.drawCard());
            }
            dealer.addCard(deck.drawCard());
            for(IPlayer player : players){
                showCardsToPlayer(player);
            }
            for(IPlayer player : players) {
                System.out.println("You have " + player.getValueOfHand() + " on hand");
                if (player.splitHand()) {
                    player.getSplitBet();
                    player.addCard(deck.drawCard());
                    addNewCardIfWanted(player);
                    player.changeActiveHand();
                    player.addCard(deck.drawCard());
                    addNewCardIfWanted(player);
                    player.changeActiveHand();
                } else {
                    addNewCardIfWanted(player);
                }
            }
            while(dealer.wannaNextCard()){
                dealer.addCard(deck.drawCard());
            }
            System.out.println("Dealer has " + dealer.getValueOfHand() + " on hand.");
            List<IPlayer> listOfWinners = winner();
            for(IPlayer p : listOfWinners){
                setPricePool(p);
            }
            System.out.println();
            System.out.println("Congrats here are Players who have won: " + listOfWinners + ".");
            for(IPlayer player : players){
                System.out.println("Here is your bank accounts: " + player.getBank());
            }


            if(!enoughCards(deck)){
            newDeck();
            }
            for(IPlayer player : players) {
                if (!enoughMoney(player)) {

                    break;
                }
            }
            for(IPlayer player : players) {
                player.eraseHands();
            }
            dealer.eraseHands();
        }while (playersIsNotEmpty());
        System.out.println("Thx, this is end of the game.");

    }

    private boolean playersIsNotEmpty(){
        for(IPlayer player : players){
            if(player.wantContinue()){
            }else {
                System.out.println("thx for playing " + player.toString() + ".");
                players.remove(player);
            }

        }if(players.isEmpty()){
            return false;
        }else return true;
    }

    @Override
    public boolean enoughMoney(IPlayer player) {
            if (player.getBank() == 0) {
                return false;
            } else return true;
    }

    @Override
    public boolean enoughCards(IDeck IDeck) {
        return deck.getNumberOfCardsLeft() < 125;
    }
    private void newDeck(){
        deck = new Deck();
    }

    @Override
    public void showCardsToPlayer(IPlayer iPlayer) {
        for(IPlayer player : players) {
            for (ICard card : player.getHand()) {
                System.out.print(card.toString());
            }
        }
    }

    /*List<IPlayer> listOfPlayers(){
        for(int i = 0; i < numberOfPlayers; i++){
            listOfPlayers().add(player);
        }
        return listOfPlayers();
    }


    List<IPlayer> listOfBanks(){
        for(int i = 0; i < numberOfPlayers; i++){
        }
    }*/
}
