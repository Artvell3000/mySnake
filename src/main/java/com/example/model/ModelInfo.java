package com.example.model;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.fructs.Fruct;

public class ModelInfo {
    public final HashSet<Fruct> fructs;
    public final ArrayList<Coordinates> snake;
    public boolean isProtected;

    ModelInfo(HashSet<Fruct> fructs, ArrayList<Coordinates> snake, boolean isProtected){
        this.fructs = fructs;
        this.snake = snake;
        this.isProtected = isProtected;
    }
}
