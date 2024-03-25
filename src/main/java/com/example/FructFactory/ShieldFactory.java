package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Fruct;
import com.example.fructs.Shield;
import com.example.model.Model;

public class ShieldFactory extends FructFactory{

    @Override
    public Fruct getFruct(int x, int y, Model model) throws FileNotFoundException {
        return new Shield(x, y, model);
    }

    @Override
    public int getCountOfFructs() {
        return 1;
    }
    
}
