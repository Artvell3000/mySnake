package com.example.model;

import java.util.ArrayList;

import com.example.fructs.Fruct;

public class ModelInfo {
    public final ArrayList<Fruct> fructs;
    public final ArrayList<Coordinates> snake;
    public boolean isProtected;

    ModelInfo(ArrayList<Fruct> fructs, ArrayList<Coordinates> snake, boolean isProtected){
        this.fructs = fructs;
        this.snake = snake;
        this.isProtected = isProtected;
    }
}
