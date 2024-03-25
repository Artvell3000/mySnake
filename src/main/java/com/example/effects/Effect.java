package com.example.effects;

import java.io.FileNotFoundException;

import com.example.model.Model;

public abstract class Effect{
    protected final Model model;

    public Effect(Model model){
        this.model = model;
    }

    public abstract void comeTrue() throws FileNotFoundException;
}



