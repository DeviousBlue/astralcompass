package com.mciad.astralcompass;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private CardType type;
    private List<CardTrait> traits;

    //TODO: add int cost to all constructors
    public Card(CardType ct, List<CardTrait> traitList){
        traits = traitList;
        type = ct;
    }

    public Card(CardType ct, CardTrait trait){
        traits = new ArrayList<>();
        traits.add(trait);
    }

    public Card(CardType ct){
        traits = new ArrayList<>();
        type = ct;
    }

    public CardType getType() {
        return type;
    }

    public List<CardTrait> getTraits(){
        return traits;
    }

    public void addTrait(CardTrait trait){
        traits.add(trait);
    }

    public boolean hasTrait(CardTrait trait){
        for(CardTrait ct: traits){
            if(ct == trait){
                return true;
            }
        }
        return false;
    }

    public boolean isType(CardType ct){
        return (ct == type);
    }

    public boolean equals(Card otherCard){
        boolean sameType = (otherCard.getType() == this.type);
        boolean sameTraits = true;
        for(CardTrait ct : traits){
            sameTraits = (sameTraits && otherCard.hasTrait(ct));
        }
        //TODO: compare cost when determining if equal
        return (sameType && sameTraits);
    }
}
