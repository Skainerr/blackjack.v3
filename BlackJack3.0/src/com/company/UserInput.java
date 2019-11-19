package com.company;

import java.util.Scanner;

public class UserInput implements IUserInput {
    private Scanner scanner = new Scanner(System.in);
    private int numOfPlayers;
    private int bank;
    private int bet;
    private String name;
    @Override
    public boolean wantNextCard() {
       try {
            System.out.println("Do you want next card?: Y/N");
            String userInput = scanner.next();
            if (userInput.equals("Y") || userInput.equals("y")) {
                return true;
            } else {
                return false;
            }
        }catch(java.util.InputMismatchException e){
        throw new java.util.InputMismatchException();
       }
    }


    @Override
    public int getBet(String name) {
        try{
        this.name = name;
        System.out.println("How much do you want to bet " + name + "?: ");
        bet = scanner.nextInt();
        return bet;
        }catch(java.util.InputMismatchException e){
            throw new java.util.InputMismatchException();
        }
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
        try{
        this.name = name;
        System.out.println("Do you want to play next game " + name + "?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
        }catch(java.util.InputMismatchException e){
            throw new java.util.InputMismatchException();
        }
    }

    @Override
    public boolean wantSplit(String name) {
        try{
        this.name = name;
        System.out.println("Do you want to split your hand " + name + "?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
        }catch(java.util.InputMismatchException e){
            throw new java.util.InputMismatchException();
        }
    }

    @Override
    public int numberOfPlayers() {
        try{
        System.out.println("How many players are going to play? max is 10 players: ");
        numOfPlayers = scanner.nextInt();
        while (numOfPlayers > 10){
            System.out.println("Sorry you can not play with that many players, maximum players is 10");
            System.out.println("How many players are going to play? max is 10 players: ");
            numOfPlayers = scanner.nextInt();
        }
        return numOfPlayers;
        }catch(MyRunTimeExceptions e){
            throw new MyRunTimeExceptions();
        }
    }

    @Override
    public int getInitialBank(String name) {
        try{
        this.name = name;
        System.out.println("How much money you want to put in " + name + "?: ");
        bank = scanner.nextInt();
        return bank;
        }catch(java.util.InputMismatchException e){
            throw new java.util.InputMismatchException();
        }
    }
   /* maybe will add later
   public boolean wannaRaiseBank() {
        System.out.println("Do you want to raise your bank?: Y/N");
        String userInput = scanner.next();
        if (userInput.equals("Y") || userInput.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    */

    public String getName(){
        try{
            System.out.println("please enter name of player: ");
        String playerName = scanner.next();
        return playerName;
        }catch(java.util.InputMismatchException e){
            throw new java.util.InputMismatchException();
        }
    }
}
