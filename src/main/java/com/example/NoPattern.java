package com.example;

import java.io.FileNotFoundException;
import java.util.Random;

import com.example.FructFactory.BombFactory;
import com.example.FructFactory.FructFactory;
import com.example.FructFactory.GoldAppleFactory;
import com.example.FructFactory.ShieldFactory;
import com.example.fructs.Fruct;
import com.example.fructs.Shield;
import com.example.model.Coordinates;
import com.example.model.Model;

public class NoPattern {
    public void generateFruct(Model model, FructFactory f) throws FileNotFoundException{

        boolean checkTheConditions = true;

        if(f instanceof BombFactory){
            checkTheConditions = model.getScore() > 30;
        }
        if(f instanceof ShieldFactory){
            checkTheConditions = model.getScore() > 50;
        }

        if(f instanceof GoldAppleFactory){
            Random r = new Random();
            int ch = r.nextInt()%100;
            checkTheConditions =  (model.getScore() > 0 && ch < 21 && model.getCountOfFreeCells() > 20);
        }

        Coordinates cell = model.getRandomFreeCell();
        Fruct newFruct = f.getFruct(cell.r, cell.c, model);
        model.addFruct(newFruct);

        if(f instanceof GoldAppleFactory){
            int startR = newFruct.getRow()-1;
            int startC = newFruct.getCol()-1;
            int endR = newFruct.getRow()+1;
            int endC = newFruct.getCol()+1;

            if(startR < 0) startR++;
            if(startC <0) startC++;
            if(endC > model.width) endC--;
            if(endR > model.height) endR--;

            for(int r=startR;r<=endR;r++){
                for(int c=startC;c<=endC;c++){
                    if(c == newFruct.getCol()) continue;
                    BombFactory factory = new BombFactory();
                    Fruct sideFruct = factory.getFruct(r, c, model);
                    try {
                        model.addFruct(sideFruct);
                    } catch (Exception e) {
                        System.out.println(r + " " + c);
                    }
                
                }
            }
        }
    }
}
