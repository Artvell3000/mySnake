package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Fruct;
import com.example.fructs.GoldApple;

public class GoldAppleFactory extends FructFactory{

    @Override
    public Fruct getFruct(int x, int y) throws FileNotFoundException {
        return new GoldApple(x,y);
    }

    @Override
    public int getCountOfFructs() {
        return 1;
    }
    
}
