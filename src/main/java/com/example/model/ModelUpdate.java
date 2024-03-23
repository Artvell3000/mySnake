package com.example.model;

import java.util.ArrayList;

import com.example.fructs.Fruct;

public class ModelUpdate {
    public final SnakeUpdate snakeUpdate;
    public final ArrayList<Fruct> newFructs;
    public final ArrayList<Fruct> deadFructs;

    ModelUpdate(SnakeUpdate snakeUpdate, ArrayList<Fruct> newFructs, ArrayList<Fruct> deadFructs){
        this.snakeUpdate = snakeUpdate;
        this.newFructs = newFructs;
        this.deadFructs = deadFructs;
    }
}
