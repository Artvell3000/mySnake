package com.example.ObserverPanes;

import java.io.FileNotFoundException;

import com.example.Game;
import com.example.model.Model;
import com.example.model.ModelUpdate;

public class GameObserver implements Observer{
    Model model;
    Game game;

    public GameObserver(Model model, Game game){
        this.model = model;
        this.game = game;
        model.registerObservers(this);
    }

    @Override
    public void update() throws FileNotFoundException {
        ModelUpdate u = model.getUpdateModelInfo();
        if(u.changeSpeed)game.increaseSpeed();
    }
    
}
