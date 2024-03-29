package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Fruct;
import com.example.fructs.Portal;
import com.example.model.Coordinates;
import com.example.model.Model;

public class PortalFactory extends FructFactory{

    @Override
    public int getCountOfFructs() {
        return 1;
    }

    @Override
    public Fruct getFruct(int x, int y, Model model) throws FileNotFoundException {
       return new Portal(x,y,model);
    }

    @Override
    public Fruct addMainFruct(Model model) throws FileNotFoundException {
        Coordinates cell1 = model.getRandomFreeCell();
        Fruct newFruct1 = this.getFruct(cell1.r, cell1.c, model);
        model.addFruct(newFruct1);

        Coordinates cell2 = model.getRandomFreeCell();
        Fruct newFruct2 = this.getFruct(cell2.r, cell2.c, model);
        model.addFruct(newFruct2);

        ((Portal)newFruct1).setSecondPortal((Portal)newFruct2);
        ((Portal)newFruct2).setSecondPortal((Portal)newFruct1);
        return newFruct1;
    }
    
}
