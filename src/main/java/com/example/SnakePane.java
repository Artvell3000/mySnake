package com.example;

import javafx.scene.layout.Pane;

public class SnakePane extends Pane{
    private String styleProtected;
    private String styleNoProtected;
    private boolean isProtected; 

    SnakePane(boolean isProtected){
        this.setStyle(styleNoProtected);
        this.isProtected = isProtected;
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
