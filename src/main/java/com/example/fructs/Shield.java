package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resoueces;
import com.example.effects.*;

public class Shield extends Fruct{

    public Shield(int r, int c) throws FileNotFoundException {
        super(r,c);
        symbol = "s";
        setPathForImage(Resoueces.pathToImgShield);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList(new ShieldEffect(), new DeleteEatenObjEffect()));
    }
    
    @Override
    public boolean isApple() {
        return false;
    }
}