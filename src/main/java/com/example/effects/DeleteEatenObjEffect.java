package com.example.effects;

import java.io.FileNotFoundException;

import com.example.model.Model;

public class DeleteEatenObjEffect extends Effect {

    
    public DeleteEatenObjEffect(Model model) {
        super(model);
    }

    @Override
    public void comeTrue() throws FileNotFoundException {
        model.deleteEatenObject();
    }
    
}
