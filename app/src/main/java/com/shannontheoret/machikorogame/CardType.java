package com.shannontheoret.machikorogame;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

public enum CardType {
    WHEAT("Wheat", "wheat", new Card(1, 1, 1, Card.Color.BLUE)),
    RANCH("Ranch", "ranch", new Card(2, 1, 1, Card.Color.BLUE)),
    BAKERY("Bakery", "bakery", new Card(new HashSet<Integer>(Arrays.asList(2,3)), 1,1, Card.Color.GREEN)),
    CAFE("Cafe", "cafe", new Card(3, 1, 2, Card.Color.RED)),
    CONVENIENCE_STORE("Convenience Store", "conv_store", new Card(4, 3, 2, Card.Color.GREEN)),
    FOREST("Forest", "forest", new Card(5, 1, 3, Card.Color.BLUE)),
    TRAIN_STATION("Train Station", "train_station", new Card(4)),
    SHOPPING_MALL("Shopping Mall", "shopping_mall", new Card(10)),
    AMUSEMENT_PARK("Amusement Park", "amusement_park", new Card(16)),
    RADIO_TOWER("Radio Tower", "radio_tower", new Card(22))
    ;
    //STADIUM("Stadium", "stadium", new com.shannontheoret.machikorogame.Card()),
    //TV_STATION("TV Station", "tv_station", new com.shannontheoret.machikorogame.Card()),
    //BUSINESS_CENTER("Business Center", "business", new com.shannontheoret.machikorogame.Card());

    private String title;
    private String label;
    private Card card;
    public static EnumSet<CardType> NORMAL_CARDS = EnumSet.range(WHEAT, FOREST);

    private CardType(String title, String label, Card card){
        this.title = title;
        this.label = label;
        this.card = card;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public Card getCard() {
        return card;
    }

    public static CardType getCardTypeFromTitle(String title) {
        for (CardType cardType : CardType.values()) {
            if(cardType.getTitle().equals(title)) {
                return cardType;
            }
        }
        return null;
    }

}
