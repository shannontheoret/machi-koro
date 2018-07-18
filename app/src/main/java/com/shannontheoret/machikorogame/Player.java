package com.shannontheoret.machikorogame;

import java.util.EnumSet;
import java.util.Set;

public class Player {
    PlayerHand hand = new PlayerHand();
    Boolean isTurn = false;
    Integer coins = 0;

    public void purchaseCard(CardType card) throws Exception{
        if(coins < card.getCard().cost){
            throw new Exception("Cannot purchase card, not a sufficient amount of coins.");
        }
        if(CardStock.stock.get(card) <= 0){
            throw new Exception("Cannot purchse card, card is not in stock.");
        }
        coins -= card.getCard().cost;
        CardStock.decreaseByOne(card);
        hand.increaseByOne(card);
    }

    public Set<CardType> getCardsThatApplyToRoll(Integer rollValue){

        Set<CardType> cardsThatApply = EnumSet.noneOf(CardType.class);

        for(CardType cardType : hand.cards.keySet()) {
            if (cardType.getCard().cardApplies(rollValue, isTurn)){
                cardsThatApply.add(cardType);
            }
        }
        return cardsThatApply;
    }

    public Boolean hasWon(){
        if(hand.cards.get(CardType.TRAIN_STATION) > 0
                && hand.cards.get(CardType.SHOPPING_MALL) > 0
                && hand.cards.get(CardType.AMUSEMENT_PARK) > 0
                && hand.cards.get(CardType.RADIO_TOWER) > 0){
            return true;
        } else {
            return false;
        }
    }
}
