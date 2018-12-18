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

    private Deck deck;
    private Map<Hero, String> charLabels;
    private Map<CardType, String> typeLabels;
    private Description desc;
    private Context ctx;
    private PieDataSet pieDataSet;
    private Map<CardType, Integer> colorSet;
    private PieChart pie;

    private List<PieEntry> entries = new ArrayList<PieEntry>();

    public SpirePie(Context context, PieChart pieChart, Deck newDeck) {
        pie = pieChart;
        ctx = context;
        deck = newDeck;
        charLabels = buildCharLabels();
        typeLabels = buildTypeLabels();
        colorSet = buildColors();
        desc = buildDescription();

        pie.setDescription(desc);
        pie.setHoleRadius(60f);
        pie.setTransparentCircleAlpha(0);
        pie.setDrawEntryLabels(true);
        pie.setCenterTextSize(18);
        pie.setUsePercentValues(true);

    }

    public void redraw(){

        entries = updatePercentages();

        pieDataSet = new PieDataSet(entries, "- Card Types");
        pieDataSet.setColors(getUsedColors());
        pieDataSet.setSliceSpace(3);
        pie.setData(new PieData(pieDataSet));

        pie.invalidate();  //refresh and redraw
    }

    private Description buildDescription(){
        desc = new Description();
        desc.setText(charLabels.get(deck.hero()));
        return desc;
    }

    private int[] getUsedColors(){
        List<Integer> colors = new ArrayList<>();
        int typeCount = 0;

        for(CardType type : CardType.values()){
            if(deck.getCount(type) > 0){
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
     * Retrieve percentages from deck counts and
     * load the data into PieEntry Objects
     * @return an array of pie chart entry data
     */
    private ArrayList<PieEntry> updatePercentages(){
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        float percentValue = 3.00f;
        for(CardType type : CardType.values()){
            percentValue = deck.percent(type);
            if(percentValue > 0)
                entries.add(new PieEntry(percentValue, typeLabels.get(type)));
        }
        pie.setCenterText(deck.size()+"\n Cards");
        return entries;
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
