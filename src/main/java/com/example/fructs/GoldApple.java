package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.Effect;
import com.example.effects.PositiveEffect;
import com.example.effects.RegenerateEffect;
import com.example.model.Model;

public class GoldApple extends Fruct{

    public GoldApple(int r, int c, Model model) throws FileNotFoundException {
        super(r, c, model);
        symbol = "g";
        setPathForImage(Resources.pathToImgGoldApple);
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<>(Arrays.asList((
            new PositiveEffect(model)).setInc(100), 
            new RegenerateEffect(model))
            );
    }

    @Override
    public boolean isApple() {
        return true;
    }
    
}
