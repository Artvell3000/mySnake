package com.example.ObserverPanes;

import java.awt.Label;

import com.example.model.Model;

public class LabelObserver implements Observer{
    Label label;

    LabelObserver(Model model, Label label){
        this.label = label;
        model.registerObservers(this);
    }

    @Override
    public void update(Object b) {

    }
    
}
