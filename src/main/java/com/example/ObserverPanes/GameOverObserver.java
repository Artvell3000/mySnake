package com.example.ObserverPanes;

import java.io.FileNotFoundException;

import com.example.model.Model;
import com.example.model.ModelUpdate;

import javafx.scene.control.Label;

public class GameOverObserver implements Observer{
    Model model;
    Label label;

    public GameOverObserver(Model model, Label lGameOver){
        this.model = model;
        this.label = lGameOver;
        model.registerObservers(this);
    }

    @Override
    public void update() throws FileNotFoundException {
        ModelUpdate u = model.getUpdateModelInfo();
        if(u.isGameOver){
            label.setVisible(true);
        }
    }
    
}
