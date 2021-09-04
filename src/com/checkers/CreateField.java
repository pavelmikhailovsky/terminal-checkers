package com.checkers;

public class CreateField {
    private final String[][] field;

    public CreateField(String[][] array) throws Exception {
        this.field = array;
        this.create(this.field);
    }

    protected String[][] getField() {
        return this.field;
    }

    private void create(String[][] array) throws Exception {
//      Create game field on given array

        for(int i = 0; i < array.length; i++) {
            if(i % 2 == 0) {

                for(int j = 0; j < array.length; j++) {
                    this.field[i][j] = "===";
                }

            } else {
                int numberHorizontalField;

                numberHorizontalField = getNumberHorizontalField(i);
                if(numberHorizontalField == 0) {
                    throw new Exception("numberHorizontalField is zero!!!");
                }

                for(int j = 0; j < array.length; j++) {
                    char symbolVerticalField = getSymbolVerticalField(j);

                    int d = Character.compare(symbolVerticalField, ' ');
                    if(d < 0) {
                        throw new Exception("symbolVerticalField is value empty!!!");
                    }

                    createWithOddI(i, j, numberHorizontalField, symbolVerticalField);
                }
            }
        }
    }

    private void createWithOddI(int i, int j, int numberHorizontalField, char symbolVerticalField) {
        if(j % 2 == 0) {
            this.field[i][j] = "||";
        }

        if(isOneEveryDamn(i)) {
            if(isOneEveryDamn(j)) {
                this.field[i][j] = "-----";
            } else if(isThreeEveryDamn(j)) {
                this.field[i][j] = String.format(
                        " %s%s%s ", BlackChecker.color, symbolVerticalField, numberHorizontalField
                );
            }
        }

        if(isThreeEveryDamn(i)) {
            if(isThreeEveryDamn(j)) {
                this.field[i][j] = "-----";
            } else if(isOneEveryDamn(j)) {
                this.field[i][j] = String.format(
                        " %s%s%s ", WhiteChecker.color, symbolVerticalField, numberHorizontalField
                );
            }
        }
    }

    private boolean isOneEveryDamn(int index) {
        // i or j = 1, 5, 9, 13
        return index == 1 || index == 5 || index == 9 || index == 13;
    }

    private boolean isThreeEveryDamn(int index) {
        // i or j = 3, 7, 11, 15
        return index == 3 || index == 7 || index == 11 || index == 15;
    }

    private int getNumberHorizontalField(int i) {
        return switch (i) {
            case 1 -> 8;
            case 3 -> 7;
            case 5 -> 6;
            case 7 -> 5;
            case 9 -> 4;
            case 11 -> 3;
            case 13 -> 2;
            case 15 -> 1;
            default -> 0;
        };
    }

    private char getSymbolVerticalField(int j) {
        return switch (j) {
            case 1 -> 'a';
            case 3 -> 'b';
            case 5 -> 'c';
            case 7 -> 'd';
            case 9 -> 'e';
            case 11 -> 'f';
            case 13 -> 'g';
            case 15 -> 'h';
            default -> ' ';
        };
    }
}
