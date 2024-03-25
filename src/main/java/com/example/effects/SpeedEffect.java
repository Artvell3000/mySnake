package com.example.effects;

import com.example.model.Model;

public class SpeedEffect extends Effect{

    public SpeedEffect(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() {
        model.increaseSpeed();
    }
    
}
