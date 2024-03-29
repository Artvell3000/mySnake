package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.*;
import com.example.model.Model;;

public class Pepper extends Fruct{

    public Pepper(int r, int c, Model model) throws FileNotFoundException {
        super(r,c,model);
        symbol = "p";
        setPathForImage(Resources.pathToImgPepper);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList(
            (new PositiveEffect(model)).setInc(10), 
            new SpeedEffect(model), 
            new RegenerateEffect(model))
            );
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
