package com.example.effects;

import java.io.FileNotFoundException;

import com.example.fructs.Portal;
import com.example.model.Model;

public class TeleportEffect extends Effect{
    Portal cord;

    public TeleportEffect(Model model, Portal cord) {
        super(model);
        this.cord = cord;
    }

    @Override
    public void comeTrue() throws FileNotFoundException {
        if(!cord.isOut()){
            model.addToSnakePlan(cord.getCoordinates());
            cord.secondPortal.updateIsOut(true);
        }
    }
    
}
