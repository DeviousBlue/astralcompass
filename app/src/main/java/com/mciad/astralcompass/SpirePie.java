package com.mciad.astralcompass;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class SpirePie {

    private Hero hero;
    private Map<CardType, Integer> cardsCount = new HashMap<CardType, Integer>();
    private float deckSize = 0;
    private Map<Hero, String> charLabels;
    private Map<CardType, String> typeLabels;
    private Context ctx;
    private PieDataSet pieDataSet;
    private Map<CardType, Integer> colorSet;
    private PieChart pie;

    private List<PieEntry> entries = new ArrayList<PieEntry>();

    public SpirePie(Context context, PieChart pieChart) {
        pie = pieChart;
        ctx = context;
        charLabels = buildCharLabels();
        typeLabels = buildTypeLabels();
        colorSet = buildColors();

        pie.setHoleRadius(60f);
        pie.setTransparentCircleAlpha(0);
        pie.setDrawEntryLabels(true);
        pie.setCenterTextSize(18);
        pie.setUsePercentValues(true);

    }

    public void redraw(){

        //re-calculate percentages from counts
        entries = calculatePercentages();

        pieDataSet = new PieDataSet(entries, "- Card Types");
        pieDataSet.setColors(getUsedColors());
        pieDataSet.setSliceSpace(3);
        pie.setData(new PieData(pieDataSet));

        pie.invalidate();  //refresh and redraw
    }

    public void setCharacter(Hero character){
        hero = character;
        Description desc = new Description();
        desc.setText(charLabels.get(hero));
        pie.setDescription(desc);

        // setup default cardsCount for current char
        switch(hero){
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
        cardsCount.put(CardType.CURSE, 0);
    }

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

    private void addCard(CardType cardType){
        cardsCount.put(cardType, cardsCount.get(cardType)+1);
        deckSize++;
    }

    private void removeCard(CardType cardType){
        int count = cardsCount.get(cardType);
        if(count>0){
            cardsCount.put(cardType, count-1);
            deckSize--;
        }
    }

    private int[] getUsedColors(){
        List<Integer> colors = new ArrayList<>();
        int typeCount = 0;

        for(Map.Entry<CardType, Integer> entry: cardsCount.entrySet()){
            CardType type = entry.getKey();
            if(cardsCount.get(type) > 0){
                colors.add(colorSet.get(type));
                typeCount++;
            }
        }

        int[] usedColors = new int[typeCount];
        for(int x=0; x<typeCount; x++){
            usedColors[x] = colors.get(x);
        }

        return usedColors;
    }

    private HashMap<Hero, String> buildCharLabels(){
        HashMap<Hero, String> temp = new HashMap<Hero, String>();
        temp.put(Hero.IRONCLAD, "The Ironclad");
        temp.put(Hero.DEFECT, "The Defect");
        temp.put(Hero.SILENT, "The Silent");
        return temp;
    }

    private HashMap<CardType, String> buildTypeLabels(){
        HashMap<CardType, String> temp = new HashMap<CardType, String>();
        temp.put(CardType.ATTACK, "attack");
        temp.put(CardType.SKILL, "skill");
        temp.put(CardType.POWER, "power");
        temp.put(CardType.COLORLESS, "colorless");
        temp.put(CardType.CURSE, "curse");
        return temp;
    }

    /**
     * Re-calculate percentages from current card counts and
     * load the result data into PieEntry Objects
     * @return an array of pie chart entry data
     */
    private ArrayList<PieEntry> calculatePercentages(){
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        float percentValue = 3.00f;
        for(Map.Entry<CardType, Integer> entry: cardsCount.entrySet()){
            percentValue = entry.getValue()/deckSize;
            if(percentValue > 0)
                entries.add(new PieEntry(percentValue, typeLabels.get(entry.getKey())));
        }
        pie.setCenterText((int)deckSize+"\n Cards");
        return entries;
    }

    private void setDefaultIronCladCards(){
        cardsCount.put(CardType.ATTACK, 6);
        cardsCount.put(CardType.SKILL, 4);
        cardsCount.put(CardType.POWER, 0);
        cardsCount.put(CardType.COLORLESS, 0);
        deckSize = 10;
    }

    private void setDefaultSilentCards(){
        cardsCount.put(CardType.ATTACK, 6);
        cardsCount.put(CardType.SKILL, 6);
        cardsCount.put(CardType.POWER, 0);
        cardsCount.put(CardType.COLORLESS, 0);
        deckSize = 12;
    }

    private void setDefaultDefectCards(){
        cardsCount.put(CardType.ATTACK, 4);
        cardsCount.put(CardType.SKILL, 6);
        cardsCount.put(CardType.POWER, 0);
        cardsCount.put(CardType.COLORLESS, 0);
        deckSize = 10;
    }


    private Map<CardType, Integer> buildColors(){
        Map<CardType, Integer> temp = new HashMap<>();
        temp.put(CardType.ATTACK, ContextCompat.getColor(ctx, R.color.redAttack));
        temp.put(CardType.SKILL, ContextCompat.getColor(ctx, R.color.greenSkill));
        temp.put(CardType.POWER, ContextCompat.getColor(ctx, R.color.blueBlock));
        temp.put(CardType.COLORLESS, ContextCompat.getColor(ctx, R.color.colorless));
        temp.put(CardType.CURSE, ContextCompat.getColor(ctx, R.color.curse));
        return temp;
    }

}
