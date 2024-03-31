package com.example.model;

import java.util.ArrayList;

import com.example.fructs.Fruct;

public class ModelUpdate {
    public SnakeUpdate snakeUpdate;
    public ArrayList<Fruct> newFructs = new ArrayList<>();
    public ArrayList<Fruct> deadFructs = new ArrayList<>();

    public boolean changeProtected = false;
    public boolean changeSpeed = false;

    public boolean isProtected = false;
    public boolean isGameOver = false;

    public int score = 0;
    public int delay = 160;
}
