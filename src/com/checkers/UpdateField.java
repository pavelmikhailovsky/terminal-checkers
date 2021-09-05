package com.checkers;

public class UpdateField {
    private final String[][] field;
    private final String command;

    public String[][] getField() {
        return this.field;
    }

    protected UpdateField(String[][] array, String string) {
        this.field = array;
        this.command = string;
    }


}
