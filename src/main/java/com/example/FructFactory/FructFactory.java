package com.example.FructFactory;

import java.io.FileNotFoundException;

import com.example.fructs.Fruct;
import com.example.model.Model;

public abstract class FructFactory {
    public abstract int getCountOfFructs();

    public abstract Fruct getFruct(int x, int y, Model model) throws FileNotFoundException;
}
