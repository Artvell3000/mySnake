package com.example.ObserverPanes;

import java.io.FileNotFoundException;

import com.example.model.Model;
import com.example.model.ModelUpdate;
import com.example.myPanes.FieldGrid;

public class GridObserver implements Observer{
    FieldGrid grid;
    Model model;

    public GridObserver(Model model, FieldGrid grid){
        this.model = model;
        this.grid = grid;
        model.registerObservers(this);
    }

    @Override
    public void update() throws FileNotFoundException {
        ModelUpdate u = model.getUpdateModelInfo();
        grid.update(u);
        if(u.changeProtected) grid.update(model.getSnake(), u.isProtected);
    }
}
