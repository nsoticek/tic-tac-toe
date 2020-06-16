package com.noahsoticek.demo.models;

public class Field {

    private int column;
    private int row;

    public Field(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
