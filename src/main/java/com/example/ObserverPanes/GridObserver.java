package com.example.ObserverPanes;

import com.example.model.Model;
import com.example.model.ModelUpdate;

import javafx.scene.layout.GridPane;

public class GridObserver extends Observer{
    GridPane grid;

    GridObserver(Model model, GridPane grid){
        this.grid = grid;
        //model.registerGridPane(grid);
    }

    @Override
    public
    void update(ModelUpdate update) {
        
    }
}
