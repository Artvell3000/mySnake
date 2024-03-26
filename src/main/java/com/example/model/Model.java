package com.example.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
import com.example.myPanes.FieldGrid;

public class Model {
    public final int height;
    public final int width;
    private int score = 0;
    private double delay = 150;
    private boolean isProtected = false;

    Boolean[][] field;
    private SnakeDeque snake;
    ArrayList<Effect> effects = new ArrayList<>();

    private ArrayList<Coordinates> freeCells = new ArrayList<>();
    private HashSet<Fruct> fructs = new HashSet<>();
    private ArrayList<Fruct> newFructs = new ArrayList<>();
    private ArrayList<Fruct> deadFructs = new ArrayList<>();
    private ArrayList<FructFactory> fructFactorys = new ArrayList<>(Arrays.asList(
        new AppleFactory(),
        new BombFactory(),
        new GoldAppleFactory(),
        new PepperFactory(),
        new ShieldFactory()
    ));

    FieldGrid grid;

    public void registerGridPane(FieldGrid grid){
        this.grid = grid;
    }

    public void increaseSnake(){
        var newTail = snake.increaseSnake();
        field[newTail.r][newTail.c] = false;
        freeCells.remove(newTail);
    }

    public void increaseSpeed(){
        delay+=10;
        //update speed
    }

    public void checkGameOver(){
        if(isProtected){
            isProtected = false;
        } else {
            //message to grid 
        }
    }

    public void setShield(){
        isProtected = true;
        //message to grid
    }

    public void increaseScore(int i){
        score+=i;
    }

    public void reduceScore(int i){
        score-=i;
    }

    public void deleteEatenObject(){
        Coordinates head = snake.getFirst();

        Fruct rmFruct = null;
        for(Fruct i:fructs){
            if(i.getCoordinates().equals(head)){
                rmFruct = i;
            }
        }
        fructs.remove(rmFruct);
    }

    private Coordinates getRandomFreeCell(){
        if(freeCells.size() == 0) return null;
        Random rnd = new Random();


        int rndCell; Coordinates freeCell;
        
        do{
            rndCell = rnd.nextInt(freeCells.size());
            freeCell = freeCells.get(rndCell);
            freeCells.remove(freeCell);
        }while(field[freeCell.r][freeCell.c] != null);

        if(snake.contains(freeCell)){
            System.out.println("field" + field[freeCell.r][freeCell.c]);
            //System.out.println("free" + freeCells.contains(freeCell));
        }

        return freeCell;
    }

    public void generateFructs() throws FileNotFoundException{

        for(Fruct i:fructs){
            if(!i.getCoordinates().equals(snake.getFirst())){
                field[i.getRow()][i.getCol()] = null;
                freeCells.add(i.getCoordinates());  
            }
            deadFructs.add(i);
        }
        fructs.clear();

        for(FructFactory f:fructFactorys){
            for(int i=0;i<f.getCountOfFructs();i++){
                if(freeCells.isEmpty()) return;
            
                Coordinates cell = getRandomFreeCell();

                field[cell.r][cell.c] = true;
                Fruct newFruct = f.getFruct(cell.r, cell.c, this);
                fructs.add(newFruct);
                newFructs.add(newFruct);
            } 
        }
    }

    public SnakeUpdate updateSnake() throws FileNotFoundException{
        SnakeUpdate update = snake.update();

        freeCells.add(update.deadTail);
        field[update.deadTail.r][update.deadTail.c] = null;
        
        Boolean fieldState = field[update.newHead.r][update.newHead.c];

        System.out.println(fieldState);

        if(fieldState == null) {
            field[update.newHead.r][update.newHead.c] = false;
            freeCells.remove(update.newHead);
            return update;
        }

        if(fieldState){
            for(Fruct i:fructs){
                if(update.newHead.equals(i.getCoordinates())){
                    effects.addAll(i.getEffect());
                    break;
                }
            }
        }else{
            effects.add(new GameOverEffect(this));
        }

        field[update.newHead.r][update.newHead.c] = false;
        freeCells.remove(update.newHead);

        while(!effects.isEmpty()){
            Effect i = effects.get(0);
            i.comeTrue();
            effects.remove(0);
        }

        return update;
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

    public ModelUpdate getNextState() throws FileNotFoundException{
        newFructs.clear();
        deadFructs.clear();

        SnakeUpdate snakeUpdate = updateSnake();
        grid.update(new ModelUpdate(snakeUpdate, newFructs, deadFructs));

        return new ModelUpdate(snakeUpdate, newFructs, deadFructs);
    }

    //
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
            if(i!=0)m.snake.increaseSnake();
            m.printSnake();
            System.out.println();
            m.updateSnake();
        }
    }
    
}
