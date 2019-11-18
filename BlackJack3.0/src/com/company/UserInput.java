package com.company;

import java.util.Scanner;

public class UserInput implements IUserInput {
    private Scanner scanner = new Scanner(System.in);
    private int numOfPlayers;
    private int bank;
    private int bet;
    @Override
    public boolean wantNextCard() {
        System.out.println("Do you want next card?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public int getBet() {
        System.out.println("How much do you want to bet?: ");
        bet = scanner.nextInt();
        return bet;
    }

    @Override
    public boolean wantBet() {
        if(bank > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean wantContinue(String name) {
        System.out.println("Do you want to play next game " + name + "?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean wantSplit() {
        System.out.println("Do you want to split your hand?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int numberOfPlayers(){
        System.out.println("How many players are going to play? max is 10 players: ");
        numOfPlayers = scanner.nextInt();
        while (numOfPlayers > 10){
            System.out.println("Sorry you can not play with that many players, maximum players is 10");
            System.out.println("How many players are going to play? max is 10 players: ");
            numOfPlayers = scanner.nextInt();
        }
        return numOfPlayers;
    }

    @Override
    public int getInitialBank() {
        System.out.println("How much money you want to put in?: ");
        bank = scanner.nextInt();
        return bank;
    }
    public boolean wannaRaiseBank() {
        System.out.println("Do you want to raise your bank?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public String getName(){
            System.out.println("please enter name of player: ");
        String playerName = scanner.next();
        return playerName;
    }
}
