package com.mciad.astralcompass;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

public class GraphFragment extends Fragment {

    private SpirePie pie;
    private Deck deck;

    private ImageButton attackButton;
    private ImageButton skillButton;
    private ImageButton powerButton;
    private ImageButton colorlessButton;
    private ImageButton curseButton;
    private ToggleImageButton toggleAddButton;

    private ToggleImageButton vulnerableButton;
    private ToggleImageButton weakButton;
    private ToggleImageButton blockButton;
    private ToggleImageButton drawButton;
    private ToggleImageButton exhaustButton;

    private List<ToggleImageButton> traitButtons;

    private View view;
    private GraphActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.graph_page, container, false);
        activity = ((GraphActivity)getActivity());
        setupButtons();

        deck = new Deck(activity.hero);
        pie = new SpirePie(view.getContext(), (PieChart) view.findViewById(R.id.pieChart), deck);
        pie.redraw();

        return view;
    }

    private void setupButtons(){

        traitButtons = new ArrayList<>();

        toggleAddButton = view.findViewById(R.id.addRemoveButton);
        toggleAddButton.setOffImage(R.drawable.minus_button_sm);
        toggleAddButton.setOnImage(R.drawable.plus_button_sm);
        toggleAddButton.toggleOn();

        toggleAddButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                toggleAddButton.toggle();
            }
        });

        attackButton = view.findViewById(R.id.attackButton);
        attackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                //update card counts
                if(toggleAddButton.isOn()) {
                    deck.addAttack();
                }else {
                    deck.removeAttack();
                }

                clearTraitButtons();
                pie.redraw();
            }
        });

        skillButton = view.findViewById(R.id.skillButton);
        skillButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                //update card counts
                if(toggleAddButton.isOn()) {
                    deck.addSkill();
                }else{
                    deck.removeSkill();
                }

                clearTraitButtons();
                pie.redraw();
            }
        });

        powerButton = view.findViewById(R.id.powerButton);
        powerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                //update card counts
                if(toggleAddButton.isOn()){
                    deck.addPower();
                }else{
                    deck.removePower();
                }

                clearTraitButtons();
                pie.redraw();
            }
        });

        colorlessButton = view.findViewById(R.id.colorlessButton);
        colorlessButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                //update card counts

                if(toggleAddButton.isOn()){
                    deck.addColorless();
                }else{
                    deck.removeColorless();
                }

                clearTraitButtons();
                pie.redraw();
            }
        });

        curseButton = view.findViewById(R.id.curseButton);
        curseButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                //update card counts

                if(toggleAddButton.isOn()){
                    deck.addCurse();
                }else{
                    deck.removeCurse();
                }

                clearTraitButtons();
                pie.redraw();
            }
        });

        //setup Card Trait toggle buttons
        vulnerableButton = view.findViewById(R.id.vulnerableButton);
        vulnerableButton.setOnImage(R.drawable.vulnerable_on);
        vulnerableButton.setOffImage(R.drawable.vulnerable);
        vulnerableButton.toggleOff();
        vulnerableButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                vulnerableButton.toggle();
            }
        });
        traitButtons.add(vulnerableButton);

        weakButton = view.findViewById(R.id.weakButton);
        weakButton.setOnImage(R.drawable.weak_on);
        weakButton.setOffImage(R.drawable.weak);
        weakButton.toggleOff();
        weakButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                weakButton.toggle();
            }
        });
        traitButtons.add(weakButton);

        blockButton = view.findViewById(R.id.blockButton);
        blockButton.setOnImage(R.drawable.block_sm_on);
        blockButton.setOffImage(R.drawable.block_sm);
        blockButton.toggleOff();
        blockButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                blockButton.toggle();
            }
        });
        traitButtons.add(blockButton);

        drawButton = view.findViewById(R.id.drawButton);
        drawButton.setOnImage(R.drawable.draw_on);
        drawButton.setOffImage(R.drawable.draw);
        drawButton.toggleOff();
        drawButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                drawButton.toggle();
            }
        });
        traitButtons.add(drawButton);

        exhaustButton = view.findViewById(R.id.exhaustButton);
        exhaustButton.setOnImage(R.drawable.exhaust_on);
        exhaustButton.setOffImage(R.drawable.exhaust);
        exhaustButton.toggleOff();
        exhaustButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                exhaustButton.toggle();
            }
        });
        traitButtons.add(exhaustButton);

    }

    private void clearTraitButtons(){
        for(ToggleImageButton button: traitButtons){
            button.toggleOff();
        }
    }
}