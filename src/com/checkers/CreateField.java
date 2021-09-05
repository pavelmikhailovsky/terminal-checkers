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

        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {

                for (int j = 0; j < array.length; j++) {
                    this.field[i][j] = "===";
                }

            } else {
                int numberHorizontalField;

                numberHorizontalField = getNumberHorizontalField(i);
                if (numberHorizontalField == 0) {
                    throw new Exception("numberHorizontalField is zero!!!");
                }

                for (int j = 0; j < array.length; j++) {
                    char symbolVerticalField = (char) getSymbolVerticalField(j);

                    int d = Character.compare(symbolVerticalField, ' ');
                    if (d < 0) {
                        throw new Exception("symbolVerticalField is value empty!!!");
                    }

                    createWithOddI(i, j, numberHorizontalField, symbolVerticalField);
                }
            }
        }
    }

    private void createWithOddI(int i, int j, int numberHorizontalField, char symbolVerticalField) {
        if (j % 2 == 0) {
            this.field[i][j] = "||";
        }

        // Create black checkers
        if (i == 1 || i == 5) {
            addCheckers(i, j, true, BlackChecker.color, numberHorizontalField, symbolVerticalField);

        } else if(i == 3) {
            addCheckers(i, j, false, BlackChecker.color, numberHorizontalField, symbolVerticalField);

        // Create empty zone
        } else if(i == 9) {
            addCheckers(i, j, true, " ", numberHorizontalField, symbolVerticalField);

        } else if(i == 7) {
            addCheckers(i, j, false, " ", numberHorizontalField, symbolVerticalField);

        // Create white checkers
        } else if(i == 13) {
            addCheckers(i, j, true, WhiteChecker.color, numberHorizontalField, symbolVerticalField);

        } else if(i == 11 || i == 15) {
            addCheckers(i, j, false, WhiteChecker.color, numberHorizontalField, symbolVerticalField);
        }
    }

    private void addCheckers(
            int i, int j, boolean checkMethod, String string, int numberHorizontalField, char symbolVerticalField
    ) {
        boolean checkOne = checkMethod ? isOneEveryDamn(j) : isThreeEveryDamn(j);
        boolean checkThree = !checkMethod ? isOneEveryDamn(j) : isThreeEveryDamn(j);

        if (checkOne) {
            this.field[i][j] = "----";
        } else if (checkThree) {
            if (" ".equals(string)) {
                this.field[i][j] = String.format(
                        " %s%s ", symbolVerticalField, numberHorizontalField
                );
            } else {
                this.field[i][j] = String.format(
                        "%s-%s%s", string, symbolVerticalField, numberHorizontalField
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

    private int getSymbolVerticalField(int j) {
        // Return abbreviated ASCII
        return switch (j) {
            case 1 -> 97;
            case 3 -> 98;
            case 5 -> 99;
            case 7 -> 100;
            case 9 -> 101;
            case 11 -> 102;
            case 13 -> 103;
            case 15 -> 104;
            default -> 32;
        };
    }
}
