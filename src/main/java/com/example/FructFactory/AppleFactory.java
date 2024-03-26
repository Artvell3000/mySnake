package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Apple;
import com.example.fructs.Fruct;
import com.example.model.Model;

public class AppleFactory extends FructFactory {

    @Override
    public Fruct getFruct(int x, int y, Model model) throws FileNotFoundException {
        return new Apple(x, y, model);
    }

    @Override
    public int getCountOfFructs() {
        return 5;
    }
    
}
