package com.example.effects;

import java.io.FileNotFoundException;

import com.example.model.Model;

public class RegenerateEffect extends Effect{

    public RegenerateEffect(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() throws FileNotFoundException {
        model.generateFructs();
    }
    
}
