package com.example.myPanes;

import com.example.Resources;

import javafx.scene.layout.Pane;

public class SnakePane extends Pane{
    private static String styleProtected = Resources.styleProtectedSnake;
    private static String styleNoProtected = Resources.styleNoProtectedSnake;

    SnakePane(boolean isProtected){
        this.setMinWidth(40);
        this.setMinHeight(40);
        this.setStyle((isProtected)?styleProtected:styleNoProtected);
    }

    public void changeStyle(boolean isProtected){
        if(isProtected){
            this.setStyle(styleProtected);
        }
        else{
            this.setStyle(styleNoProtected);
        }
    }
}
