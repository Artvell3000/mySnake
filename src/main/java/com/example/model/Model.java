package com.example.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.example.FructFactory.AppleFactory;
import com.example.FructFactory.BombFactory;
import com.example.FructFactory.FructFactory;
import com.example.FructFactory.GoldAppleFactory;
import com.example.FructFactory.PepperFactory;
import com.example.FructFactory.ShieldFactory;
import com.example.effects.Effect;
import com.example.effects.GameOverEffect;
import com.example.fructs.Fruct;
import com.example.model.DrivingDirections.Direction;

public class Model {
    final int height;
    final int width;
    Boolean[][] field;

    private SnakeDeque snake;

    ArrayList<Effect> effects = new ArrayList<>();

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
            field[i.getRow()][i.getCol()] = null;
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
        SnakeUpdate update = snake.update();
        freeCells.add(update.deadTail);
        field[update.deadTail.r][update.deadTail.c] = null;

        //System.out.println("deadTail : " + update.deadTail.toString());
        
        Boolean fieldState = field[update.newHead.r][update.newHead.c];
        field[update.newHead.r][update.newHead.c] = false;
        

        if(fieldState == null) return;

        if(fieldState){
            for(Fruct i:fructs){
                if(update.newHead.equals(i.getCoordinates())){
                    effects.addAll(i.getEffect());
                }
            }
        }else{
            effects.add(new GameOverEffect());
        }

        ;
    }

    public Model(int h, int w) throws FileNotFoundException{
        height = h;
        width = w;
        snake = new SnakeDeque(h, w);
        field = new Boolean[h][w];
        for(int r=0;r<height;r++){
            for(int c=0;c<width;c++){
                field[r][c] = null;
                freeCells.add(new Coordinates(r, c));
            }
        }

        snake = new SnakeDeque(h, w);
        field[snake.getFirst().r][snake.getFirst().c] = false;
        freeCells.remove(snake.getFirst());

        generateFructs();
    }

    

    //get && set

    public ModelUpdate getNextState(){

        var snakeUpdate = snake.update();

        return new ModelUpdate(snakeUpdate, newFructs, deadFructs);
    }

    public ModelInfo getInfo(){
        printField();
        return new ModelInfo(fructs, snake.toArrayList(), false);
    }

    public void setDirection(Direction d){
        snake.setDirection(d);
    }

    //print

    private void printField(){
        for(int r=0;r<height;r++){

            String iter = String.valueOf(r);
            while(iter.length()!=3){
                iter+=" ";
            }

            System.out.print(iter);

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
                    if(f.getRow() == r && f.getCol() == c){
                        System.out.print(f.toString() + " ");
                        break;
                    }
                }
            }
            System.out.println();
        }
    }

    private void printSnake(){
        for(Coordinates i:snake){
            System.out.println(i.toString());
        }
    }

    public static void main(String[] arg) throws FileNotFoundException{
        Model m = new Model(23, 23);
        
        for(int i=0;i<7;i++){

            System.out.println("iter " + i + " ------------------------------------------------------------------");
            m.printField();
            System.out.println();
            if(i == 3)m.snake.increaseSnake();
            m.printSnake();
            System.out.println();
            m.updateSnake();
        }
    }
    
}
