package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput implements IUserInput{
    private Scanner scanner = new Scanner(System.in);
    private int numOfPlayers = 0;
    private int bank;
    private int bet;
    private String name;


    @Override
    public boolean wantNextCard() {
            System.out.println("Do you want next card?: Y/N");
            String userInput = scanner.next();
            userInput = userInput.toUpperCase();
            while(!userInput.equals("N") && !userInput.equals("Y")){
            System.out.println("Please enter just Y/N, thank you.");
            userInput = scanner.next();
            userInput = userInput.toUpperCase();
        }
        if(userInput.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }



    @Override
    public int getBet(String name) {
        this.name = name;
        System.out.println("How much do you want to bet " + name + "?: ");
        String userInputBet = scanner.next();
        bet = Integer.valueOf(userInputBet);
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
        this.name = name;
        System.out.println("Do you want to play next game " + name + "?: Y/N");
        String userInput = scanner.next();
        userInput = userInput.toUpperCase();
        while(!userInput.equals("N") && !userInput.equals("Y")){
            System.out.println("Please enter just Y/N, thank you.");
            userInput = scanner.next();
            userInput = userInput.toUpperCase();
        }
        if(userInput.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean wantSplit(String name) {

        this.name = name;
        System.out.println("Do you want to split your hand " + name + "?: Y/N");
        String userInput = scanner.next();
        userInput = userInput.toUpperCase();
        while(!userInput.equals("N") && !userInput.equals("Y")){
            System.out.println("Please enter just Y/N, thank you.");
            userInput = scanner.next();
            userInput = userInput.toUpperCase();
        }
        if(userInput.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int numberOfPlayers() {
        System.out.println("How many players are going to play? max is 10 players: ");
        try {
            Scanner scannerInt = new Scanner(System.in);
                numOfPlayers = scannerInt.nextInt();
            while (numOfPlayers > 10) {
                System.out.println("Sorry you can not play with that many players, maximum players is 10");
                System.out.println("How many players are going to play? max is 10 players: ");
                numOfPlayers = scanner.nextInt();
                scannerInt.close();
            }
        } catch (InputMismatchException e) {
            System.out.print("Invalid number of players. ");

            return numberOfPlayers();
        }return numOfPlayers;
    }

    @Override
    public int getInitialBank(String name) {
        this.name = name;
        System.out.println("How much money you want to put in " + name + "?: ");
        try {
            Scanner scannerInt = new Scanner(System.in);
            bank = scannerInt.nextInt();
            scannerInt.close();
        } catch (InputMismatchException e) {
            System.out.print("Could not read the amount, please enter numbers: ");

            return getInitialBank(name);
        }return bank;
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
            System.out.println("please enter name of player: ");
        String playerName = scanner.next();
        return playerName;
    }
}
