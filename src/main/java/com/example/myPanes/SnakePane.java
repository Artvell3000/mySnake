package com.example.myPanes;

import com.example.Resources;

import javafx.scene.layout.Pane;

public class SnakePane extends Pane{
    private static String styleProtected = Resources.styleProtectedSnake;
    private static String styleNoProtected = Resources.styleNoProtectedSnake;
    private boolean isProtected; 

    SnakePane(boolean isProtected){
        this.isProtected = isProtected;
        this.setMinWidth(40);
        this.setMinHeight(40);
        this.setStyle(styleNoProtected);
        //this.getChildren().add(new Label("Snake"));
    }

    public void changeStyle(){
        if(isProtected){
            this.setStyle(styleNoProtected);
        }
        else{
            this.setStyle(styleProtected);
        }
        isProtected = !isProtected;
    }
}
