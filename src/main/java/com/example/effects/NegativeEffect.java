package com.example.effects;

import com.example.model.Model;

public class NegativeEffect extends Effect{
    private int decrease;

    public NegativeEffect(Model model) {
        super(model);
    }

    public NegativeEffect setDecrease(int d){
        decrease = d;
        return this;
    }

    @Override
    public void comeTrue() {
        model.reduceScore(decrease);
    }

}