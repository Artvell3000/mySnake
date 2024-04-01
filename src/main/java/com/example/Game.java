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
    int tik = 0;
    final double delay = 10;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(delay), e -> {
                try {
                    if(model.isUpdateTik(tik))model.goToNextState();
                    tik++;
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
    
    Game() throws FileNotFoundException{
        model = new Model(height, width);
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

    public void increaseSpeed(){
        tik = 0;
    }
}
