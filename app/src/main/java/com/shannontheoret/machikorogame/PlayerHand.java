package com.shannontheoret.machikorogame;

import java.util.EnumMap;

/**
 * Created by shannonbrown on 2017-11-11.
 */

public class PlayerHand {
    public EnumMap<CardType, Integer> cards;

    public PlayerHand(){
        cards = new EnumMap<CardType, Integer>(CardType.class);
        for(CardType cardType : CardType.values()){
            cards.put(cardType, 0);
        }
        cards.put(CardType.WHEAT, 1);
        cards.put(CardType.BAKERY, 1);
    }

    public void increaseByOne(CardType cardType){
        Integer count = cards.get(cardType);
        count++;
        cards.put(cardType, count);
    }
}
