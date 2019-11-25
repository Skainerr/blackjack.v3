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
        int originalBank = userInput.getInitialBank(player.getName());
        player.addToBank(originalBank);
    }

    @Override
    public void setPricePool(IPlayer player) {
        int money = player.getBet() * 2;
        player.addToBank(money);
    }

    @Override
    public boolean nextRound() {
        return userInput.wantContinue("");
    }

    private void addNewCardIfWanted(IPlayer player){
        while (player.getValueOfHand() < 21){
            if(player.wannaNextCard()){
                player.addCard(deck.drawCard());
                showHand(player);
                System.out.println(player.getName() + " has " + player.getValueOfHand() + " on hand");
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
            String  playerName = userInput.getName();
            IPlayer player = new Player(playerName);
            players.add(player);
        }

        for(IPlayer player : players){
            moneyInBank(player);
        }

        do{
            for(IPlayer player : players) {
                player.bet();
            }
            for(IPlayer player : players) {
                player.addCard(deck.drawCard());
            }
            dealer.addCard(deck.drawCard());
            for(IPlayer player : players) {
                player.addCard(deck.drawCard());
            }
            dealer.addCard(deck.drawCard());
            showCardsToPlayer();
            for(IPlayer player : players) {
                System.out.println(player.getName() + " has " + player.getValueOfHand() + " on hand");
                if (player.splitHand()) {
                    player.getSplitBet();
                    player.addCard(deck.drawCard());
                    showCardsToPlayer();
                    addNewCardIfWanted(player);
                    player.changeActiveHand();
                    player.addCard(deck.drawCard());
                    showCardsToPlayer();
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
                System.out.println("Congrats  " + p.getName() + " you won.");
            }


            for(IPlayer player : players){
                System.out.println(player.getName() + " here is your new bank account: " + player.getBank());
            }


            if(!enoughCards(deck)){
            newDeck();
            }

            for(IPlayer player : players) {
                player.eraseHands();
            }
            dealer.eraseHands();
        }while (playersIsNotEmpty());
        System.out.println("Thx, this is end of the game.");

    }

    private boolean playersIsNotEmpty(){
        List<IPlayer> toRemove = new ArrayList<>();
        for(IPlayer player : players){
            if (!enoughMoney(player)){
                System.out.println("thx for playing " + player.getName() + ", you do not have enough money to continue.");
                toRemove.add(player);
            }else {
                if (!player.wantContinue()) {
                    System.out.println("thx for playing " + player.getName() + ".");
                    toRemove.add(player);
                }
            }
        }

        players.removeAll(toRemove);
        return !players.isEmpty();
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
    public void showCardsToPlayer() {
        for (IPlayer player : players) {
                System.out.println("Player " + player.getName() + " has this cards in hand ");
                for (ICard card : player.getHand()) {
                    System.out.print(card.toString());
                }
        }
    }

    public void showHand(IPlayer player){
        System.out.println("Player " + player.getName() + " has this cards in hand ");
        for (ICard card : player.getHand()) {
            System.out.print(card.toString());
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
