package com.example.effects;

import com.example.model.Model;

public class EnhancingEffect extends Effect{

    public EnhancingEffect(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() {
        model.increaseSnake();
    }
    
}

