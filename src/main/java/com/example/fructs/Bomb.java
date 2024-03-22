package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resoueces;
import com.example.effects.*;

public class Bomb extends Fruct{

    public Bomb(int r, int c) throws FileNotFoundException {
        super(r,c);
        symbol = "b";
        setPathForImage(Resoueces.pathToImgBomb);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList(new GameOverEffect(), new DeleteEatenObjEffect()));
    }
    
    @Override
    public boolean isApple() {
        return false;
    }
}
