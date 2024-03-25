package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.*;
import com.example.model.Model;

public class Shield extends Fruct{

    public Shield(int r, int c, Model model) throws FileNotFoundException {
        super(r,c, model);
        symbol = "s";
        setPathForImage(Resources.pathToImgShield);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList(
            new ShieldEffect(model), 
            new DeleteEatenObjEffect(model))
            );
    }
    
    @Override
    public boolean isApple() {
        return false;
    }
}