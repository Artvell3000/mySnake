package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Bomb;
import com.example.fructs.Fruct;
import com.example.model.Model;

public class BombFactory extends FructFactory{

    @Override
    public Fruct getFruct(int x, int y, Model model) throws FileNotFoundException {
        return new Bomb(x,y,model);
    }

    @Override
    public int getCountOfFructs() {
        return 5;
    }
    
}
