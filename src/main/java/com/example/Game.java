package com.example;

import java.io.FileNotFoundException;

import com.example.model.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Game {
    Model model;
    Timeline timeline;
    double delay = 150;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(delay), e -> {
                try {
                    model.getNextState();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            });
    
    
}
