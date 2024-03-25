package com.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

import com.example.model.Model;
import com.example.model.DrivingDirections.Direction;
import com.example.myPanes.FieldGrid;

public class App extends Application {
    final int height = 41, width = 21;
    final double delay = 320;
    Model model = null;
    Timeline timeline = new Timeline();
    
    private static Scene scene;

    private Label getLabelForScore(){
        Label scoreLabel = new Label("0");
        scoreLabel.setStyle(Resources.styleScore);
        return scoreLabel;
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException{

        model = new Model(height, width);
        
        FieldGrid grid = new FieldGrid(height, width);

        VBox vbox = new VBox();
        vbox.getChildren().add(getLabelForScore());
        vbox.getChildren().add(grid);

        StackPane root = new StackPane();
        
        Label l = new Label("GAME OVER");
        l.setStyle(Resources.styleGameOver);
        l.setVisible(false);
        root.getChildren().add(vbox);
        root.getChildren().add(l);
        
        scene = new Scene(root);
        grid.redraw(model.getInfo());

        timeline.getKeyFrames().add(
            new KeyFrame(Duration.millis(delay), e -> {
                model.updateSnake();
                try {
                    grid.redraw(model.getInfo());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                System.out.println("updateSnake");
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            switch (keyCode.getChar()) {
                case "A":
                    model.setDirection(Direction.UP);
                    break;
                case "D":
                    model.setDirection(Direction.DOWN);
                    break;
                case "S":
                    model.setDirection(Direction.RIGHT);
                    break;
                case "W":
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