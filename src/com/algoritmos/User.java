package com.algoritmos;

import java.util.Map;

public class User {
    private Map<String,  Card> collectionOfCard;

    public Map<String, Card> getCollectionOfCard() {
        return collectionOfCard;
    }

    public void setCollectionOfCard(Map<String, Card> collectionOfCard) {
        this.collectionOfCard = collectionOfCard;
    }

    public User() {
        collectionOfCard = MapFactory.getFactory().getMap();
    }

    public void addCard(Card card){
        collectionOfCard.put(card.getName(), card);
    }
}
