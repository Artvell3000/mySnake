package com.example.effects;

import com.example.model.Model;

public class PositiveEffect extends Effect{
    public PositiveEffect(Model model) {
        super(model);
    }

    private int inc;
    public PositiveEffect setInc(int inc){
        this.inc = inc;
        return this;
    }

    @Override
    public void comeTrue() {
        model.increaseScore(inc);
    }

}
