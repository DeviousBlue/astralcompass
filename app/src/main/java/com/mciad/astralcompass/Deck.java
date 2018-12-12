package com.mciad.astralcompass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deck {

    private List<Card> cards;
    private Map<CardType, Integer> typeCount;
    private Map<CardTrait, Integer> traitCount;
    private Hero hero;

    public Deck(Hero h){
        cards = new ArrayList<>();
        hero = h;
        setupDefaultCards(hero);
    }

    public void addCard(CardType type){
        addCard(new Card(type));
    }

    public void addCard(CardType type, CardTrait trait){
        addCard(new Card(type, trait));
    }

    public void addCard(CardType type, List<CardTrait> traits){
        addCard(new Card(type, traits));
    }

    public void addCard(Card card){
        cards.add(card);
        //update type counts
        CardType type = card.getType();
        typeCount.put(type, typeCount.get(type)+1);

        //TODO: update trait counts
        for(CardTrait ct : card.getTraits()){
            switch (ct){
                case VULNERABLE:

            }
        }
    }

    public void removeCard(Card targetCard){
        for(Card localCard : cards){
            if(localCard.equals(targetCard)){
                cards.remove(localCard);
                //TODO: update counts
            }
        }
    }

    public int size(){
        return cards.size();
    }

    private void addType(CardType type){
        typeCount.put(type, typeCount.get(type)+1);
    }

    private void removeType(CardType type){
        int count = typeCount.get(type);
        if(count > 0)
            typeCount.put(type, count-1);
    }

    private void setupDefaultCards(Hero myHero){
        traitCount = new HashMap<>();
        for(CardTrait ct : CardTrait.values())
            traitCount.put(ct, 0);

        typeCount = new HashMap<>();
        for(CardType ct : CardType.values())
            typeCount.put(ct, 0);

        switch (myHero){
            case IRONCLAD:
                setDefaultIronCladCards();
                break;
            case SILENT:
                setDefaultSilentCards();
                break;
            case DEFECT:
                setDefaultDefectCards();
                break;

        }
    }

    private void setDefaultIronCladCards(){
        for(int i=0; i<5; i++)
            addCard(CardType.ATTACK);

        for(int i=0; i<4; i++)
            addCard(CardType.SKILL, CardTrait.BLOCK);

        addCard(new Card(CardType.ATTACK, CardTrait.VULNERABLE));
    }

    private void setDefaultSilentCards(){
        // 6 attack (one with weak) and 6 skills with block
        for(int i=0; i<5; i++)
            addCard(CardType.ATTACK);

        for(int i=0; i<6; i++)
            addCard(CardType.SKILL, CardTrait.BLOCK);

        addCard(new Card(CardType.ATTACK, CardTrait.VULNERABLE));
    }

    private void setDefaultDefectCards(){
        //4 attacks, 5 skills with block and 1 skill with Channel
        for(int i=0; i<4; i++)
            addCard(CardType.ATTACK);

        for(int i=0; i<5; i++)
            addCard(CardType.SKILL, CardTrait.BLOCK);

        addCard(new Card(CardType.ATTACK, CardTrait.));

        typeCount.put(CardType.ATTACK, 4);
        typeCount.put(CardType.SKILL, 6);
        typeCount.put(CardType.POWER, 0);
        typeCount.put(CardType.COLORLESS, 0);
    }
}
