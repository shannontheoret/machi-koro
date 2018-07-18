package com.shannontheoret.machikorogame;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

public class CardStock {
    public static EnumMap<CardType, Integer> stock = new EnumMap(CardType.class);

    static {
        for(CardType cardType : CardType.NORMAL_CARDS){
            stock.put(cardType, 6);
        }
    }

    public static Boolean decreaseByOne(CardType cardType){
        Integer count = stock.get(cardType);
        if(count < 1){
            return false;
        } else {
            stock.put(cardType, --count);
            return true;
        }
    }

    public static List<String> getCardStockList(){
        List<String> cardStockList = new ArrayList<>();
        for (Entry<CardType, Integer> card : stock.entrySet()) {
            cardStockList.add(card.getKey().getTitle() + " (" + card.getValue() + ")");
        }
        return cardStockList;
    }

    public static CardType getCardTypeFromCardListItem(String listItem) {
        String cardTitle = listItem.substring(0, listItem.length() - 4);
        return CardType.getCardTypeFromTitle(cardTitle);
    }
}