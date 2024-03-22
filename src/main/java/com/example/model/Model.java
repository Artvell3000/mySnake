package com.example.model;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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

    private ArrayDeque<Coordinates> snake = new ArrayDeque<>();

    private ArrayList<Coordinates> freeCells = new ArrayList<>();
    private ArrayList<Fruct> fructs = new ArrayList<>();
    private ArrayList<Fruct> newFructs = new ArrayList<>();
    private ArrayList<Fruct> deadFructs = new ArrayList<>();
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

    public void generateFructs() throws FileNotFoundException{

        while(!fructs.isEmpty()){
            Fruct i = fructs.get(0);
            field[i.r][i.c] = null;
            freeCells.add(i.getCoordinates());
            deadFructs.add(i);
            fructs.remove(0);
        }

        for(FructFactory f:fructFactorys){
            for(int i=0;i<f.getCountOfFructs();i++){
                if(freeCells.size() == 0) return;
            
                Coordinates cell = getRandomFreeCell();
                freeCells.remove(cell);

                field[cell.r][cell.c] = true;
                Fruct newFruct = f.getFruct(cell.r, cell.c);
                fructs.add(newFruct);
                newFructs.add(newFruct);
            } 
        }
    }

    public void updateSnake(){

    }

    Model(int h, int w) throws FileNotFoundException{
        height = h;
        width = w;
        field = new Boolean[h][w];
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

        generateFructs();
    }

    public void getNextState(){

    }

    public Coordinates getStartSnake(){
        return snake.getFirst();
    }

    public void printField(){
        for(int r=0;r<height;r++){

            for(int c=0;c<width;c++){
                if(field[r][c] == null){
                    System.out.print("_ ");
                    continue;
                }
                if(!field[r][c]){
                    System.out.print("0 ");
                    continue;
                }

                for(Fruct f:fructs){
                    if(f.r == r && f.c == c){
                        System.out.print(f.toString() + " ");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] arg) throws FileNotFoundException{
        Model m = new Model(23, 23);
        m.printField();
        m.generateFructs();
        System.out.println();
        m.printField();
    }
    
}
