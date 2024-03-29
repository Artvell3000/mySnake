package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.DeleteEatenObjEffect;
import com.example.effects.Effect;
import com.example.effects.TeleportEffect;
import com.example.model.Model;

public class Portal extends Fruct{
    public Portal(int r, int c, Model model) throws FileNotFoundException {
        super(r, c, model);
        symbol = "t";
        setPathForImage(Resources.pathToImgPortal);
    }

    public Portal secondPortal;
    boolean isOut = false;

    public void updateIsOut(boolean b){
        isOut = b;
    }

    public boolean isOut(){
        return isOut;
    }

    public Portal setSecondPortal(Portal p){
        secondPortal = p;
        return this;
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<Effect>(Arrays.asList(
            new TeleportEffect(model, secondPortal),
            new DeleteEatenObjEffect(model)
            ));
    }

    @Override
    public boolean isApple() {
        return false;
    }

    @Override
    public boolean isDinanic() {
        return false;
    }
    
}
