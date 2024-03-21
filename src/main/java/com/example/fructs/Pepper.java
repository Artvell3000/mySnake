package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resoueces;
import com.example.effects.*;;

public class Pepper extends Fruct{

    public Pepper(int r, int c) throws FileNotFoundException {
        super(r,c);
        setPathForImage(Resoueces.pathToImgPepper);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList((new PositiveEffect()).setInc(10), new SpeedEffect(), new RegenerateEffect()));
    }

    @Override
    public boolean isApple() {
        return false;
    }
    
}
