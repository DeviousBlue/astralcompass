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
        typeCount = new HashMap<CardType, Integer>();
        traitCount = new HashMap<CardTrait, Integer>();
        cards = new ArrayList<>();
        hero = h;
        setupDefaultCards();
    }

    public void addCard(CardType type){
        addCard(new Card(type));
    }

    /**
     *  Add a Card to this Deck by providing a CardType and CardTrait.
     *  Cards with multiple traits cannot be added this way.
     * @param type - type of card to add
     * @param trait - trait of card to add
     */
    public void addCard(CardType type, CardTrait trait){
        addCard(new Card(type, trait));
    }

    /**
     *  Add a Card to this Deck by providing a CardType and
     *  a List of CardTraits.
     * @param type - type of card to add
     * @param traits - traits of card to add
     */
    public void addCard(CardType type, List<CardTrait> traits){
        addCard(new Card(type, traits));
    }

    /** Add a Card to this Deck
     * @param card - card to be added
     */
    public void addCard(Card card){
        cards.add(card);

        //update counts
        addType(card.getType());
        for(CardTrait ct : card.getTraits()){
            addTrait(ct);
        }
    }

    /** Remove a Card from this Deck
     * @param targetCard - card to be removed
     */
    public void removeCard(Card targetCard){

        for(Card localCard : cards){
            // Custom comparison rather than cards.contains()
            if(localCard.equals(targetCard)){
                cards.remove(localCard);

                // update counts
                removeType(localCard.getType());
                for(CardTrait ct : localCard.getTraits()){
                    removeTrait(ct);
                }
                break;
            }
        }
    }

    public void removeCard(CardType type){
        removeCard(new Card(type));
    }

    /**
     * Several convenience methods for quickly
     * performing common card management actions
     */

    public void addAttack(){
        addCard(CardType.ATTACK);
    }

    public void addPower(){
        addCard(CardType.POWER);
    }

    public void addSkill(){
        addCard(CardType.SKILL);
    }

    public void addColorless(){
        addCard(CardType.COLORLESS);
    }

    public void addCurse() {
        addCard(CardType.CURSE);
    }

    public void removeAttack(){
        removeCard(CardType.ATTACK);
    }

    public void removePower(){
        removeCard(CardType.POWER);
    }

    public void removeSkill(){
        removeCard(CardType.SKILL);
    }

    public void removeColorless(){
        removeCard(CardType.COLORLESS);
    }

    public void removeCurse(){
        removeCard(CardType.CURSE);
    }

    public Hero hero(){
        return hero;
    }


    /** Get the number of cards in this deck
     * @return card count as an int
     */
    public int size(){
        return cards.size();
    }

    public int getCount(CardType type){
        return typeCount.get(type);
    }

    public int getCount(CardTrait trait){
        return traitCount.get(trait);
    }

    public float percent(CardType type){
        return (float)getCount(type)/size();
    }

    public float percent(CardTrait trait){
        return (float)getCount(trait)/size();
    }

    /** Increment the count for the given card type
     * @param type -  target type count to increment
     */
    private void addType(CardType type){
        typeCount.put(type, typeCount.get(type)+1);
    }

    /** Decrement the count for the given card type
     * @param type -  target type count to decrement
     */
    private void removeType(CardType type){
        int count = typeCount.get(type);
        if(count > 0)
            typeCount.put(type, count-1);
    }

    /** Increment the count for the given card trait
     * @param trait -  target trait count to increment
     */
    private void addTrait(CardTrait trait){
        traitCount.put(trait, traitCount.get(trait)+1);
    }

    /** Decrement the count for the given card trait
     * @param trait -  target trait count to decrement
     */
    private void removeTrait(CardTrait trait){
        int count = traitCount.get(trait);
        if(count > 0)
            traitCount.put(trait, count-1);
    }

    /** Create all default cards for a given hero and
     * set all initial card and trait counts to starting values
     */
    private void setupDefaultCards(){
        traitCount = new HashMap<>();
        for(CardTrait ct : CardTrait.values())
            traitCount.put(ct, 0);

        typeCount = new HashMap<>();
        for(CardType ct : CardType.values())
            typeCount.put(ct, 0);

        switch (hero){
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
        // 6 attack (one with weak) and
        // 6 skills with block (one also has discard)
        for(int i=0; i<5; i++)
            addCard(CardType.ATTACK);

        for(int i=0; i<5; i++)
            addCard(CardType.SKILL, CardTrait.BLOCK);

        addCard(new Card(CardType.ATTACK, CardTrait.VULNERABLE));

        Card survivor = new Card(CardType.SKILL, CardTrait.BLOCK);
        survivor.addTrait(CardTrait.DISCARD);
        addCard(survivor);
    }

    private void setDefaultDefectCards(){
        //4 attacks, 4 skills with block
        // 1 skill with Channel and 1 skill with evoke(?)
        for(int i=0; i<4; i++)
            addCard(CardType.ATTACK);

        for(int i=0; i<4; i++)
            addCard(CardType.SKILL, CardTrait.BLOCK);

        addCard(new Card(CardType.SKILL, CardTrait.CHANNEL));
        addCard(new Card(CardType.SKILL, CardTrait.EVOKE));
    }
}
