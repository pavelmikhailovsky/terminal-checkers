package com.checkers;

public class UpdateField {
    private final String[][] field;
    private final String checker;
    private final String stepChecker;
    private int CurrentCheckerIndexI;
    private int CurrentCheckerIndexJ;

    public String[][] getField() {
        return this.field;
    }

    protected UpdateField(String[][] array, String string) {
        this.field = update(array);
        this.checker = insertCharacter(string.substring(0, 3));
        this.stepChecker = string.substring(4);
    }

    private String insertCharacter(String str) {
        StringBuilder sbr = new StringBuilder(str);
        return sbr.insert(1, '-').toString();
    }

    private String[][] update(String[][] array) {
        // find index i and j
        findIndex(array);

        String[][] updateField = array;
        return updateField;
    }

    private void findIndex(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j].equals(this.checker)) {
                    this.CurrentCheckerIndexI = i;
                    this.CurrentCheckerIndexJ = j;
                }
            }
        }
    }
}
