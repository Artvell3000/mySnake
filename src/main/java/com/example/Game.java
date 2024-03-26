package com.example;

import java.io.FileNotFoundException;

import com.example.model.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Game {
    int height = 41, width = 21;
    boolean isPaused = false;
    Model model;
    Timeline timeline = new Timeline();
    double delay = 150;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(delay), e -> {
                try {
                    model.getNextState();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
    
    Game() throws FileNotFoundException{
        model = new Model(height, width);

        model.registerGame(this);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void changeState(){
        if(isPaused){
            timeline.play();
        }else{
            timeline.stop();
        }
        isPaused = !isPaused;
    } 

    public void increaseSpeed(double d){
        delay = d;
        //System.out.println("timeline start");
        timeline.stop();
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), e -> {
            try {
                model.getNextState();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        //System.out.println("timeline end");
    }

}
