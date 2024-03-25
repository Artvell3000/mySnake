package com.example.effects;

import java.io.FileNotFoundException;

import com.example.model.Model;

public class ExitTheField extends Effect{

    public ExitTheField(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() throws FileNotFoundException {
        model.getExitTheField();
    }
    
}
