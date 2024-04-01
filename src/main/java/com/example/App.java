package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import com.example.ObserverPanes.GameObserver;
import com.example.ObserverPanes.GameOverObserver;
import com.example.ObserverPanes.GridObserver;
import com.example.ObserverPanes.ScoreObserver;
import com.example.model.DrivingDirections.Direction;
import com.example.myPanes.FieldGrid;

public class App extends Application {
    final int height = 41, width = 21;
    final double delay = 150;
    boolean isPaused = true;

    GameObserver gameObserver;
    ScoreObserver scoreObserver;
    GameOverObserver gameOverObserver;
    GridObserver gridObserver;

    Label lScore = getLabelForScore();
    Label lPause = getLabelForPause();
    Label lGameOver = getLabelForGameOver();
    FieldGrid grid;
    VBox vbox = new VBox();

    Game game;
    
    private static Scene scene;

    private Label getLabelForScore(){
        Label scoreLabel = new Label("0");
        scoreLabel.setStyle(Resources.styleScore);
        return scoreLabel;
    }

    private Label getLabelForPause(){
        Label pLabel = new Label(Resources.textPause);
        pLabel.setStyle(Resources.stylePause);
        pLabel.setVisible(false);
        return pLabel;
    }

    private Label getLabelForGameOver(){
        Label gameOverLabel = new Label(Resources.textGameOver);
        gameOverLabel.setStyle(Resources.styleGameOver);
        gameOverLabel.setVisible(false);
        return gameOverLabel;
    }

    private void restartGame() throws FileNotFoundException{
        game.model.deleteAllObservers();
        game = new Game();

        vbox.getChildren().remove(grid);
        
        grid = new FieldGrid(game.model);

        vbox.getChildren().add(grid);

        gameObserver = new GameObserver(game.model, game);
        scoreObserver = new ScoreObserver(game.model, lScore);
        gameOverObserver = new GameOverObserver(game.model, lGameOver);
        gridObserver = new GridObserver(game.model, grid);

        grid.redraw(game.model.getInfo());
        lGameOver.setVisible(false);
    }

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws FileNotFoundException{

        game = new Game();
        grid = new FieldGrid(game.model);

        gameObserver = new GameObserver(game.model, game);
        scoreObserver = new ScoreObserver(game.model, lScore);
        gameOverObserver = new GameOverObserver(game.model, lGameOver);
        gridObserver = new GridObserver(game.model, grid);

        vbox.getChildren().add(lScore);
        vbox.getChildren().add(grid);

        StackPane root = new StackPane();
        
        root.getChildren().add(vbox);
        root.getChildren().add(lGameOver);
        root.getChildren().add(lPause);
        
        scene = new Scene(root);
        grid.redraw(game.model.getInfo());

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();

            switch (keyCode.getChar()) {
                case "A":
                    game.model.setDirection(Direction.UP);
                    break;
                case "D":
                    game.model.setDirection(Direction.DOWN);
                    break;
                case "S":
                    game.model.setDirection(Direction.RIGHT);
                    break;
                case "W":
                    game.model.setDirection(Direction.LEFT);
                    break;
                case "R":
                    try {
                        this.restartGame();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "O":
                    game.changeState();
                    if(isPaused){
                        lPause.setVisible(true);
                    }
                    else {
                        lPause.setVisible(false);
                    }
                    isPaused = !isPaused;
                    break;
            }
        });

        stage.setTitle(Resources.textTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}