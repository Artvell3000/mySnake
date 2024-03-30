package com.example.ObserverPanes;

import java.io.FileNotFoundException;

import com.example.model.Model;
import com.example.model.ModelUpdate;

public class GridObserver implements Observer{
    FieldGrid grid;

    GridObserver(Model model){
        model.registerObservers(this);
        grid = new FieldGrid(model);
    }

    @Override
    public void update(Object b) throws FileNotFoundException {
        grid.update((ModelUpdate)b);
    }
}
