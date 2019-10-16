package com.company;

import java.util.List;

public class Dealer extends Player {
    @Override
    public boolean wannaNextCard() {
        return getValueOfHand() < 17;
    }
    @Override
    public void addToBank(int money) {
        throw new RuntimeException("Dealer does not have bank");
    }

    @Override
    public int getBank(){
        throw new RuntimeException("Dealer does not have bank");
    }

    @Override
    public boolean wantContinue(){
        {
            throw new RuntimeException("Dealer has to continue");
        }
    }

    @Override
    public boolean splitHand(){
        throw new RuntimeException("Dealer can not split hand");
    }

    @Override
    public boolean wantSplitHand(){
        throw new RuntimeException("Dealer can not split hand");
    }

    @Override
    public int bet(){
        throw new RuntimeException("Dealer can not bet");
    }

    @Override
    public boolean wantBet(){
        throw new RuntimeException("Dealer can not bet");
    }

    @Override
    public List<ICard> getHand(){
        throw new RuntimeException("no need for dealer to know his cards");
    }

    @Override
    public void changeActiveHand(){
        throw new RuntimeException("Dealer can not split hand => only one hand for dealer");
    }
}