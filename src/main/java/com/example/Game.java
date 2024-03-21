package com.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import com.example.Snake.SnakeCell;
import com.example.FructFactory.AppleFactory;
import com.example.FructFactory.BombFactory;
import com.example.FructFactory.FructFactory;
import com.example.FructFactory.PepperFactory;
import com.example.FructFactory.ShieldFactory;
import com.example.effects.Effect;
import com.example.fructs.Bomb;
import com.example.fructs.Fruct;
import com.example.fructs.GoldApple;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game {
    private double delay = 160;
    private Label scoreLabel;
    private Snake snake;
    private int score = 0;
    private Label root;
    private ArrayList<Fruct> fructsOnField = new ArrayList<>();
    private ArrayList<Effect> effects = new ArrayList<>();
    private ArrayList<FructFactory> allFactory = new ArrayList<>();
    private Timeline timeline;
    private Pane[][] field = new Pane[23][23];
    private static Game instance;

    public void repeatGame() throws FileNotFoundException{
        
        ArrayList<Fruct> fructsOnFiedl = new ArrayList<>(); 
        Snake snake = new Snake(field, 23, 23);

        this.snake = snake;
        this.fructsOnField = fructsOnFiedl;
        this.regenerateFructs();
    }

    public void deleteEatenObject(){
        SnakeCell c = snake.getHead();
        var arr = fructsOnField;
        for(int i=0;i<arr.size();i++){
            if(arr.get(i).x == c.x && arr.get(i).y == c.y){
                    field[c.x][c.y].getChildren().removeIf(x -> x instanceof ImageView);
                    arr.remove(i);
                    return;
            }
        }
    }

    public Snake getSnake(){
        return snake;
    }

    public Pane[][] getField(){
        return field;
    }

    public Timeline getTimeline(){
        return timeline;
    }

    public ArrayList<Effect> getEffects(){
        return effects;
    }

    private boolean isFreeCell(int x, int y, ArrayList<Fruct> objectOnFiedl){
        if(objectOnFiedl.isEmpty()) return true;

        for(int i=0;i<objectOnFiedl.size();i++){
            if(objectOnFiedl.get(i).getRow() == x && objectOnFiedl.get(i).getCol() == y){
                return false;
            }
        }

        var head = snake.getHead();
        var startXH = head.x - 2;
        var endXH = head.x + 2;
        var startYH = head.y - 2;
        var endYH = head.y + 2;

        if((startXH <= x) && (x <= endXH) && (startYH <= y) && (y <= endYH)){
            return false;
        }

        var l = snake.getSnakeArray();
        for(int i=0;i<l.size();i++){
            if(l.get(i).y == y && l.get(i).x == x){
                return false;
            }
        }
        return true;
    }

    public void regenerateFructs() throws FileNotFoundException{

        while(!fructsOnField.isEmpty()){
            var i = fructsOnField.get(0);
            field[i.x][i.y].getChildren().removeIf(x -> x instanceof ImageView);
            fructsOnField.remove(0);
        }

        Random rnd = new Random();
        boolean chanse = rnd.nextInt(20) < 20;
        if(chanse){
            int i = 0;
            int r,c;
            do{
                r = rnd.nextInt(20) + 1;
                c = rnd.nextInt(20) + 1;
                if(i == 0) break;
                i++;
            } while(!isFreeCell(c, r, fructsOnField));

            Fruct gold = new GoldApple(r,c);
            fructsOnField.add(gold);
            ImageView imageView = new ImageView(gold.image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            field[c][r].getChildren().add(imageView);

            for(i=gold.x - 1; i <= gold.x+1; i++){
                for(int j=gold.y-1;j<=gold.y+1;j++){
                    if(gold.y != j && isFreeCell(j, i, fructsOnField)){
                        Fruct bomb = new Bomb(j,i);
                        fructsOnField.add(bomb);
                        ImageView imageV = new ImageView(bomb.image);
                        imageV.setFitWidth(40);
                        imageV.setFitHeight(40);
                        field[i][j].getChildren().add(imageV);
                    }
                }
            }    
            
        }


        for(FructFactory f: allFactory){
            for(int i=0; i<5; i++){
                int r,c;
                do{
                    r = rnd.nextInt(23);
                    c = rnd.nextInt(23);
                } while(!isFreeCell(c, r, fructsOnField));
                Fruct bomb = f.getFruct(r,c);
                fructsOnField.add(bomb);
                ImageView imageView = new ImageView(bomb.image);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                field[c][r].getChildren().add(imageView);
            }
        }
    }

    public ArrayList<Fruct> getFructsOnField(){
        return fructsOnField;
    }

    public void stopGame(){
        timeline.stop();
        root.setVisible(true);
    }

    public void speedIncrease(){
        timeline.stop();
        timeline.getKeyFrames().remove(0);

        delay -= 10;
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.millis(delay), e -> {
                snake.updateSnake();
                ArrayList<Effect> effects = Game.getInstance().getEffects();
                while(effects.size()!=0){
                    try {
                        effects.get(0).comeTrue();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    effects.remove(0);
                }
                System.out.println("update");
            })
        );
        timeline.play();
    }

    public int increaseScore(int inc){
        score += inc;
        scoreLabel.setText(String.valueOf(score));
        return score;
    }

    public int decreaseScore(){
        score -= 5;
        scoreLabel.setText(String.valueOf(score));
        return score;
    }

    private Game(Snake snake, ArrayList<Fruct> fructsOnField, Label score, Pane[][] field, Timeline t, Label root){
        this.snake = snake;
        this.fructsOnField = fructsOnField;
        this.scoreLabel = score;
        this.field = field;
        this.timeline = t;
        this.root = root;
        allFactory.add(new AppleFactory());
        allFactory.add(new BombFactory());
        allFactory.add(new PepperFactory());
        allFactory.add(new ShieldFactory());
    }

    public static Game getInstance(){
        return instance;
    }

    public static Game getInstance(Snake snake, ArrayList<Fruct> fructsOnFiedl, Label score, Pane[][] field, Timeline t, Label root){
        instance = new Game(snake, fructsOnFiedl, score, field, t, root);
        return instance;
    }
    
}
