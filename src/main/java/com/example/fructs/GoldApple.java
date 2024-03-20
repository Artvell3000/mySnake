package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.effects.Effect;
import com.example.effects.PositiveEffect;
import com.example.effects.RegenerateEffect;

public class GoldApple extends Fruct{

    public GoldApple(int y, int x) throws FileNotFoundException {
        super(Resoueces.pathToImgGoldApple, y, x);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList((new PositiveEffect()).setInc(100), new RegenerateEffect()));
    }

    @Override
    public boolean isApple() {
        return true;
    }
    
}
