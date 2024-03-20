package com.example;

import javafx.scene.layout.Pane;

public class FieldPane extends Pane{
    public final int r;
    public final int c;

    public boolean isEmpty(){
        return this.getChildren().size() == 0;
    }

    public void updateGameObject(){

    }

    FieldPane(int r, int c){
        this.r = r;
        this.c = c;
    }
}
