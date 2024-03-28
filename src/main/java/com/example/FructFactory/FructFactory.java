package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Fruct;
import com.example.model.Coordinates;
import com.example.model.Model;

public abstract class FructFactory {
    public abstract int getCountOfFructs();

    public abstract Fruct getFruct(int x, int y, Model model) throws FileNotFoundException;

    public void generateFruct(Model model) throws FileNotFoundException{

        if(model.isFilled()) return;

        if(checkTheConditions(model)){
            Fruct f = addMainFruct(model);
            addSideFructs(model,f);
        }
    }

    public boolean checkTheConditions(Model model){
        return true;
    }

    public Fruct addMainFruct(Model model) throws FileNotFoundException{
        Coordinates cell = model.getRandomFreeCell();
        Fruct newFruct = this.getFruct(cell.r, cell.c, model);
        model.addFruct(newFruct);
        return newFruct;
    }

    public void addSideFructs(Model model, Fruct f) throws FileNotFoundException{
        return;
    }
}
