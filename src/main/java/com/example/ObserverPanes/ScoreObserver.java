package com.example.ObserverPanes;

import com.example.model.Model;
import com.example.model.ModelUpdate;

import javafx.scene.control.Label;

public class ScoreObserver implements Observer{
    Label label;
    Model model;

    public ScoreObserver(Model model, Label label){
        this.label = label;
        this.model = model;
        model.registerObservers(this);
    }

    @Override
    public void update() {
        ModelUpdate u = model.getUpdateModelInfo();
        label.setText(model.getScoreString());
    }
    
}
