package com.shannontheoret.machikorogame;

import java.util.LinkedList;
import java.util.Set;

/**
 * Created by shannonbrown on 2017-11-11.
 */

public class Game {
    public static Player player1 = new Player();
    //TODO: create better data set for this
    public static LinkedList<Player> players = new LinkedList<>();
    public static Player currentPlayer = player1;
    public static Boolean canRollDice = true;
    public static Boolean canBuyCard = false;

    static{
        player1.isTurn = true;
        players.add(player1);
    }

    public static void handleRoll(Integer rollValue) throws Exception {
        if (!canRollDice) {
            throw new Exception("Cannot roll dice more than once per turn.");
        }
        for(Player player : players){
            Set<CardType> cardsThatApply = player.getCardsThatApplyToRoll(rollValue);
            for(CardType card : cardsThatApply){
                player.coins += card.getCard().reward * player.hand.cards.get(card);
                //TODO: handle more complicated cards in a case statement
            }
        }
        canRollDice = false;
        canBuyCard = true;
    }

    public static Boolean handleEndTurn() throws Exception {
        if (canRollDice) {
            throw new Exception("Cannot end turn. Please roll the dice.");
        }
        if (currentPlayer.hasWon()){
            return true;
        }
        //TODO: switch whose turn it is
        canRollDice = true;
        canBuyCard = false;
        return false;
    }

    public static void handlePurchase(String cardStockListItem) throws Exception {
        if (!canBuyCard) {
            throw new Exception("Cannot purchase card before rolling the dice.");
        }
        CardType cardType = CardStock.getCardTypeFromCardListItem(cardStockListItem);
        currentPlayer.purchaseCard(cardType);
        canBuyCard = false;
    }
}
