package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.*;
import com.example.model.Model;

public class Apple extends Fruct{

    public Apple(int r, int c, Model model) throws FileNotFoundException {
        super(r,c, model);
        symbol = "a";
        setPathForImage(Resources.pathToImgApple);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList(
            (new PositiveEffect(model)).setInc(10), 
            new EnhancingEffect(model), 
            new RegenerateEffect(model))
        );
    }
    
    @Override
    public boolean isApple() {
        return true;
    }

    @Override
    public boolean isDinanic() {
        return false;
    }
}