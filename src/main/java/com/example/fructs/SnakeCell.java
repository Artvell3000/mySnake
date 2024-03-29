package com.example.fructs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.Resources;
import com.example.effects.Effect;
import com.example.effects.GameOverEffect;
import com.example.model.Model;

import javafx.scene.layout.Pane;

public class SnakeCell extends Fruct{
    private boolean isProtected = false;
    //private boolean isHead = false;

    protected SnakeCell(int r, int c, boolean isProtected, Model model /*, boolean isHead*/) throws FileNotFoundException {
        super(r, c, model);
        symbol = "O";
        //this.isHead = isHead;
        this.isProtected = isProtected;
        //setPathForImage(Resoueces.sty);
        
    }

    @Override
    public ArrayList<Effect> getEffect() {
        return new ArrayList<Effect>(Arrays.asList(new GameOverEffect(model)));
    }

    @Override
    public boolean isApple() {
        return false;
    }
    
    public Pane getSnakePane(){
        Pane pane = new Pane();
        pane.setStyle(
            (isProtected)?Resources.styleProtectedSnake:Resources.styleNoProtectedSnake  
        );
        return pane;
    }

    @Override
    public boolean isDinanic() {
        return false;
    }
}
