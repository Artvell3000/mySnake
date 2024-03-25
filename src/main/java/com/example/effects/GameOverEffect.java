package com.example.effects;

import com.example.model.Model;

public class GameOverEffect extends Effect{

    public GameOverEffect(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() {
        model.checkGameOver();
    }

}
