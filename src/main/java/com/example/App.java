package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import com.example.effects.Effect;
import com.example.fructs.*;
import com.example.model.Model;
import com.example.model.DrivingDirections.Direction;

public class App extends Application {
    final int height = 21, width = 21;
    final double delay = 160;
    Model model = null;

    Timeline timeline = new Timeline();
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws FileNotFoundException{

        model = new Model(height, width);
        
        FieldGrid grid = new FieldGrid(height, width);

        Label scoreLabel = new Label("0");
        scoreLabel.setStyle("-fx-font-size:20");
        

        VBox vbox = new VBox();
        vbox.getChildren().add(scoreLabel);
        vbox.getChildren().add(grid);

        StackPane root = new StackPane();
        
        Label l = new Label("GAME OVER");
        l.setStyle("-fx-font-size:50");
        l.setVisible(false);
        root.getChildren().add(vbox);
        root.getChildren().add(l);
        
        scene = new Scene(root);
        grid.redraw(model.getInfo());

        timeline.getKeyFrames().add(
            new KeyFrame(Duration.millis(delay), e -> {
                
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            switch (keyCode.getChar()) {
                case "W":
                    model.setDirection(Direction.UP);
                    break;
                case "S":
                    model.setDirection(Direction.DOWN);
                    break;
                case "D":
                    model.setDirection(Direction.RIGHT);
                    break;
                case "A":
                    model.setDirection(Direction.LEFT);
                    break;
                case "O":
                    timeline.stop();
                    break;
            }
            System.out.println("Key Pressed: " + keyCode.getChar());
        });

        stage.setTitle("SNAKE");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}