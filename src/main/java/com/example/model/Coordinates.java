package com.example.model;

public class Coordinates {
    public int r;
    public int c;
    public Coordinates(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates cord = (Coordinates)obj;
        return cord.r == this.r && cord.c == this.c;
    }

    public Coordinates copy(){
        return new Coordinates(r, c);
    }

    @Override
    public String toString(){
        return "( " + r + " , " + c + " )";
    }
}
