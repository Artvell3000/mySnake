package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.effects.Effect;
import com.example.effects.GameOverEffect;

public class SnakeCell extends Fruct{

    protected SnakeCell(int r, int c, boolean isProtected, boolean isHead) throws FileNotFoundException {
        super(r, c);
        
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<Effect>(Arrays.asList(new GameOverEffect()));
    }

    @Override
    public boolean isApple() {
        return false;
    }
    
}
