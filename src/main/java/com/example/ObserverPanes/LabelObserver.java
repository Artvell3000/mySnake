package com.example.ObserverPanes;

import java.awt.Label;

import com.example.model.Model;
import com.example.model.ModelUpdate;

public class LabelObserver implements Observer{
    Label label;

    LabelObserver(Model model, Label label){
        this.label = label;
    }

    @Override
    public void update(Object b) {

    }
    
}
