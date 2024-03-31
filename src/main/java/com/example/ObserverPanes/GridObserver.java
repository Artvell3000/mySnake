package com.example.ObserverPanes;

import java.io.FileNotFoundException;

import com.example.model.Model;
import com.example.model.ModelUpdate;

public class GridObserver implements Observer{
    FieldGrid grid;
    Model model;

    GridObserver(Model model){
        this.model = model;
        model.registerObservers(this);
        grid = new FieldGrid(model);
    }

    @Override
    public void update() throws FileNotFoundException {
        grid.update(model.getUpdateModelInfo());
    }
}
