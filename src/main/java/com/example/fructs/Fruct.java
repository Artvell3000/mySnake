package com.example.fructs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.example.effects.Effect;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fruct{
    public final int r;
    public final int c;
    public static String path = "";

    public static void setPathForImage(String s){
        path = s;
    }
    
    public int getRow(){
        return r;
    }

    public int getCol(){
        return c;
    }

    protected Fruct(int r, int c) throws FileNotFoundException{
        this.r = r;
        this.c = c;
    }

    public ImageView getImage() throws FileNotFoundException{
        Image image = new Image(new FileInputStream(path));
        return new ImageView(image);
    }

    public abstract ArrayList<Effect> getEffect();
    public abstract boolean isApple();
}
