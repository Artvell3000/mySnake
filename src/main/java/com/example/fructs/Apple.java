package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.*;

public class Apple extends Fruct{

    public Apple(int r, int c) throws FileNotFoundException {
        super(r,c);
        symbol = "a";
        setPathForImage(Resources.pathToImgApple);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList((new PositiveEffect()).setInc(10), new EnhancingEffect(), new RegenerateEffect()));
    }
    
    @Override
    public boolean isApple() {
        return true;
    }
}