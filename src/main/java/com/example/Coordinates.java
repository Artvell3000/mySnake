package com.example;

public class Coordinates {
    public int r;
    public int c;
    Coordinates(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinates cord = (Coordinates)obj;
        return cord.r == this.r && cord.c == this.c;
    }
}
