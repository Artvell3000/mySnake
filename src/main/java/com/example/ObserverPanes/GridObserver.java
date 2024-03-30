package com.example.ObserverPanes;

import com.example.model.Model;

import javafx.scene.layout.GridPane;

public class GridObserver implements Observer{
    GridPane grid;

    GridObserver(Model model, GridPane grid){
        this.grid = grid;
        //model.registerGridPane(grid);
    }

    @Override
    public void update(Object b) {
        
    }
}
