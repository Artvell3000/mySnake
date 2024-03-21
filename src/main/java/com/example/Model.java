package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.example.FructFactory.AppleFactory;
import com.example.FructFactory.BombFactory;
import com.example.FructFactory.FructFactory;
import com.example.FructFactory.GoldAppleFactory;
import com.example.FructFactory.PepperFactory;
import com.example.FructFactory.ShieldFactory;
import com.example.fructs.Fruct;

public class Model {
    final int height;
    final int width;
    Boolean[][] field;

    private ArrayList<Coordinates> snake = new ArrayList<>();
    private ArrayList<Coordinates> freeCells = new ArrayList<>();
    private ArrayList<Fruct> fructs = new ArrayList<>();
    private ArrayList<FructFactory> fructFactorys = new ArrayList<>(Arrays.asList(
        new AppleFactory(),
        new BombFactory(),
        new GoldAppleFactory(),
        new PepperFactory(),
        new ShieldFactory()
    ));

    private Coordinates getRandomFreeCell(){
        if(freeCells.size() == 0) return null;

        Random rnd = new Random();
        int rndCell = rnd.nextInt(freeCells.size());

        return freeCells.get(rndCell);
    }

    public void generateFructs(){
        for(FructFactory f:fructFactorys){
            if(freeCells.size() == 0) break;
            
            Coordinates cell = getRandomFreeCell();
            freeCells.remove(cell);

            field[cell.r][cell.c] = true;

            
        }
    }

    Model(int h, int w){
        height = h;
        width = w;
        for(int r=0;r<height;r++){
            for(int c=0;c<width;c++){
                field[r][c] = null;
                freeCells.add(new Coordinates(r, c));
            }
        }

        Coordinates head = new Coordinates(h/2, w/2);
        snake.add(head);
        field[head.r][head.c] = false;
        freeCells.remove(head);


    }

    public Coordinates getStartSnake(){
        return snake.get(0);
    }

    public static void main(String[] arg){
        Coordinates c1 = new Coordinates(0, 0);
        Coordinates c2 = new Coordinates(0, 0);
        ArrayList<Coordinates> c = new ArrayList<>();
        c.add(c1);
        System.out.println(c.contains(c2));
    }
    
}
