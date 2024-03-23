package com.example.fructs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.example.effects.Effect;
import com.example.model.Coordinates;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fruct{
    public Coordinates coordinate;
    public String path = "";
    public String symbol = "";

    public Coordinates getCoordinates(){
        return coordinate;
    }

    @Override
    public String toString(){
        return symbol;
    }

    public void setPathForImage(String s){
        path = s;
    }
    
    public int getRow(){
        return coordinate.r;
    }

    public int getCol(){
        return coordinate.c;
    }

    protected Fruct(int r, int c) throws FileNotFoundException{
        coordinate = new Coordinates(r, c);
    }

    public ImageView getImageView() throws FileNotFoundException{
        Image image = new Image(new FileInputStream(path));
        ImageView view = new ImageView(image);
        view.setFitHeight(40);
        view.setFitWidth(40);
        return view;
    }

    public abstract ArrayList<Effect> getEffect();
    public abstract boolean isApple();
}
