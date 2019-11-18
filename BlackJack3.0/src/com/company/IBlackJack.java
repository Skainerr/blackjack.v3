package com.company;

import java.util.List;

public interface IBlackJack {

    /*Return players that win this round*/
    public List<IPlayer> winner();
    /*Take player and gives him his price*/
    public void setPricePool(IPlayer IPlayer);
    public boolean nextRound();
    public void game();
    public boolean enoughMoney(IPlayer player);
    public boolean enoughCards(IDeck IDeck);
    public void showCardsToPlayer();


}
