package com.example.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import com.example.Game;
import com.example.FructFactory.AppleFactory;
import com.example.FructFactory.BombFactory;
import com.example.FructFactory.FructFactory;
import com.example.FructFactory.GoldAppleFactory;
import com.example.FructFactory.PepperFactory;
import com.example.FructFactory.PortalFactory;
import com.example.FructFactory.ShieldFactory;
import com.example.ObserverPanes.FieldGrid;
import com.example.ObserverPanes.Observer;
import com.example.effects.Effect;
import com.example.effects.GameOverEffect;
import com.example.fructs.Fruct;
import com.example.model.DrivingDirections.Direction;

public class Model {
    public final int height;
    public final int width;

    Boolean[][] field;
    SnakeDeque snake;
    ModelUpdate modelUpdate = new ModelUpdate();
    ArrayList<Effect> effects = new ArrayList<>();

    private ArrayList<Coordinates> freeCells = new ArrayList<>();
    private HashSet<Fruct> fructs = new HashSet<>();
    private ArrayList<FructFactory> fructFactorys = new ArrayList<>(Arrays.asList(
        new AppleFactory(),
        new BombFactory(),
        new GoldAppleFactory(),
        new PepperFactory(),
        new ShieldFactory(),
        new PortalFactory()
    ));

    ArrayList<Observer> observers = new ArrayList<>();

    public ModelUpdate getUpdateModelInfo(){
        return modelUpdate;
    }

    public void registerObservers(Observer b){
        observers.add(b);
    }

    public boolean deleteObservers(Observer b){
        return observers.remove(b);
    }

    public void notifyObservers() throws FileNotFoundException{

        for(Observer i:observers){
            i.update();
        }
    }

    //FieldGrid grid;
    Game game;

    public void addToSnakePlan(Coordinates c){
        snake.addToPlan(c);
    }

    public boolean isFilled(){
        return freeCells.isEmpty();
    }

    public boolean isFreeCell(Coordinates cord){
        return freeCells.contains(cord);
    }

    /*
    public void registerGridPane(FieldGrid grid){
        this.grid = grid;
    }

    */
    public void registerGame(Game game){
        this.game = game;
    }
    // */

    public void increaseSnake(){
        var newTail = snake.increaseSnake();
        field[newTail.r][newTail.c] = false;
        freeCells.remove(newTail);
    }

    public void increaseSpeed(){
        modelUpdate.delay-=30;
        modelUpdate.changeSpeed = true;
        //game.increaseSpeed(modelUpdate.delay);
    }

    public void checkGameOver(){
        if(modelUpdate.isProtected){
            modelUpdate.isProtected = false;
            modelUpdate.changeProtected = true;
            //grid.update(snake.toArrayList(), modelUpdate.isProtected);
        } else {
            modelUpdate.isGameOver = true; 
        }
    }

    public void setShield() throws FileNotFoundException{
        modelUpdate.isProtected = true;
        modelUpdate.changeProtected = true;
        //grid.update(snake.toArrayList(), modelUpdate.isProtected);
    }

    public void increaseScore(int i){
        modelUpdate.score+=i;
    }

    public void reduceScore(int i){
        modelUpdate.score-=i;
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

    public Coordinates getRandomFreeCell(){
        if(freeCells.size() == 0) return null;
        Random rnd = new Random();


        int rndCell; Coordinates freeCell;
        
        //do{
            rndCell = rnd.nextInt(freeCells.size());
            freeCell = freeCells.get(rndCell);
            
        //}while(field[freeCell.r][freeCell.c] != null);
        

        return freeCell;
    }

    public boolean addFruct(Fruct f){
        if(field[f.getRow()][f.getCol()]!=null) return false;

        field[f.getRow()][f.getCol()] = true;
        fructs.add(f);
        modelUpdate.newFructs.add(f);
        freeCells.remove(f.getCoordinates());
        return true;
    }

    public void generateFructs() throws FileNotFoundException{

        for(Fruct i:fructs){
            if(!i.getCoordinates().equals(snake.getFirst())){
                field[i.getRow()][i.getCol()] = null;
                freeCells.add(i.getCoordinates());  
            }
            modelUpdate.deadFructs.add(i);
        }
        fructs.clear();

        for(FructFactory f:fructFactorys){
            for(int i=0;i<f.getCountOfFructs();i++){
                f.generateFruct(this);
            } 
        }
    }

    public SnakeUpdate updateSnake() throws FileNotFoundException{
        modelUpdate.snakeUpdate = snake.update();

        freeCells.add(modelUpdate.snakeUpdate.deadTail);
        field[modelUpdate.snakeUpdate.deadTail.r][modelUpdate.snakeUpdate.deadTail.c] = null;
        
        Boolean fieldState = field[modelUpdate.snakeUpdate.newHead.r][modelUpdate.snakeUpdate.newHead.c];

        //System.out.println(fieldState);

        if(fieldState == null) {
            field[modelUpdate.snakeUpdate.newHead.r][modelUpdate.snakeUpdate.newHead.c] = false;
            freeCells.remove(modelUpdate.snakeUpdate.newHead);
            return modelUpdate.snakeUpdate;
        }

        if(fieldState){
            for(Fruct i:fructs){
                if(modelUpdate.snakeUpdate.newHead.equals(i.getCoordinates())){
                    effects.addAll(i.getEffect());
                    break;
                }
            }
        }else{
            effects.add(new GameOverEffect(this));
        }

        field[modelUpdate.snakeUpdate.newHead.r][modelUpdate.snakeUpdate.newHead.c] = false;
        freeCells.remove(modelUpdate.snakeUpdate.newHead);

        while(!effects.isEmpty()){
            Effect i = effects.get(0);
            i.comeTrue();
            effects.remove(0);
        }

        return modelUpdate.snakeUpdate;
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

    public ArrayList<Coordinates> getSnake(){
        return snake.toArrayList();
    }

    public int getCountOfFreeCells(){
        return freeCells.size();
    }

    public void goToNextState() throws FileNotFoundException{
        if (modelUpdate.isGameOver) {
            return;
        }

        modelUpdate.newFructs.clear();
        modelUpdate.deadFructs.clear();
        modelUpdate.changeProtected = false;
        modelUpdate.changeSpeed = false;

        updateSnake();

        notifyObservers();
        //grid.update(modelUpdate);
    }

    public ModelInfo getInfo(){
        return new ModelInfo(fructs, snake.toArrayList(), false);
    }

    public void setDirection(Direction d){
        if((snake.direction == Direction.DOWN && d == Direction.UP) || 
        (snake.direction == Direction.UP && d == Direction.DOWN) || 
        (snake.direction == Direction.LEFT && d == Direction.RIGHT) || 
        (snake.direction == Direction.RIGHT && d == Direction.LEFT)
        ) return;
        snake.setDirection(d);
    }

    private void printField(){
        System.out.println("FIELD :");
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
        System.out.println("SNAKE : ");
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

    public String getScoreString() {
        return String.valueOf(modelUpdate.score);
    }

    public int getScore(){
        return modelUpdate.score;
    }
    
}
