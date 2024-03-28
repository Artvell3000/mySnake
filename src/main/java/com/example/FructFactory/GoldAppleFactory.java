package com.example.FructFactory;

import java.io.FileNotFoundException;
import java.util.Random;

import com.example.fructs.Fruct;
import com.example.fructs.GoldApple;
import com.example.model.Model;

public class GoldAppleFactory extends FructFactory{

    @Override
    public Fruct getFruct(int x, int y, Model model) throws FileNotFoundException {
        return new GoldApple(x,y, model);
    }

    @Override
    public int getCountOfFructs() {
        return 1;
    }

    @Override
    public boolean checkTheConditions(Model model){
        Random r = new Random();
        int ch = r.nextInt()%100;
        return model.getScore() > 0 && ch < 21 && model.getCountOfFreeCells() > 20;
    }

    @Override
    public void addSideFructs(Model model, Fruct f) throws FileNotFoundException{
        int startR = f.getRow()-1;
        int startC = f.getCol()-1;
        int endR = f.getRow()+1;
        int endC = f.getCol()+1;

        if(startR < 0) startR++;
        if(startC <0) startC++;
        if(endC > model.width) endC--;
        if(endR > model.height) endR--;

        for(int r=startR;r<=endR;r++){
            for(int c=startC;c<=endC;c++){
                if(c == f.getCol()) continue;
                BombFactory factory = new BombFactory();
                Fruct newFruct = factory.getFruct(r, c, model);
                try {
                    model.addFruct(newFruct);
                } catch (Exception e) {
                    System.out.println(r + " " + c);
                }
                
            }
        }
    }
    
}
