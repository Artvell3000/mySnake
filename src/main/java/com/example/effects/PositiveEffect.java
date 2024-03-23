package com.example.effects;

public class PositiveEffect extends Effect{
    int inc;
    public PositiveEffect setInc(int inc){
        this.inc = inc;
        return this;
    }

    @Override
    public void comeTrue() {
        //Game.getInstance().increaseScore(inc);
    }

}
