package com.example.effects;

import java.io.FileNotFoundException;

import com.example.model.Model;

public class ShieldEffect extends Effect{

    public ShieldEffect(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() throws FileNotFoundException {
        model.setShield();
    }
    
}
