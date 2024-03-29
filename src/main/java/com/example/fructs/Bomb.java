package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.*;
import com.example.model.Model;

public class Bomb extends Fruct{

    public Bomb(int r, int c, Model model) throws FileNotFoundException {
        super(r,c, model);
        symbol = "b";
        setPathForImage(Resources.pathToImgBomb);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList(
            new GameOverEffect(model), 
            new DeleteEatenObjEffect(model))
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
