package com.example.model;

public class SnakeUpdate {
    public final Coordinates newHead;
    public final Coordinates deadTail;

    SnakeUpdate(Coordinates newHead, Coordinates deadTail){
        this.newHead = newHead;
        this.deadTail = deadTail;
    }
}
