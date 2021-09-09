package com.checkers;

public class UpdateField {
    private final String[][] field;
    private final String checker;
    private final String stepChecker;
    private final int currentCheckerIndexI;
    private final int currentCheckerIndexJ;
    private final int stepCheckerIndexI;
    private final int stepCheckerIndexJ;

    public String[][] getField() {
        return this.field;
    }

    protected UpdateField(String[][] array, String string) throws Exception {
        this.checker = insertCharacter(string.substring(0, 3));
        this.stepChecker = insertSpaces(string.substring(4));
        FindIndexTuple2 findIndexCurrentChecker = new FindIndexTuple2(array, this.checker);
        FindIndexTuple2 findIndexCurrentStepChecker = new FindIndexTuple2(array, this.stepChecker);
        this.currentCheckerIndexI = findIndexCurrentChecker.getIndexI();
        this.currentCheckerIndexJ = findIndexCurrentChecker.getIndexJ();
        this.stepCheckerIndexI = findIndexCurrentStepChecker.getIndexI();
        this.stepCheckerIndexJ = findIndexCurrentStepChecker.getIndexJ();
        this.field = update(array);
    }

    private String insertCharacter(String str) {
        StringBuilder sbr = new StringBuilder(str);
        return sbr.insert(1, '-').toString();
    }

    private String insertSpaces(String str) {
        return String.format(" %s ", str);
    }

    private String[][] update(String[][] array) throws Exception {
        if (this.stepCheckerIndexI == 0 && this.stepCheckerIndexJ == 0) throw new Exception("Move is not possible");

        if (equalCharactersVertical(this.checker, this.stepChecker)) throw new Exception("Move is not possible(char)");

        if (equalNumbersHorizontal(this.checker, this.stepChecker)) throw new Exception("Move is not possible(num)");

        // char => int => 97...104
        int characterChecker = this.checker.charAt(2);
        int characterStepChecker = this.stepChecker.charAt(1);

        if (characterChecker + 1 == characterStepChecker || characterChecker - 1 == characterStepChecker) {
            return changeField(array);
        } else if (characterChecker + 2 == characterStepChecker) {

            if (this.checker.charAt(3) + 1 == this.stepChecker.charAt(2) - 1) {
                String intermediateCage = insertSpaces(plusCharacter(this.checker.charAt(2))
                        .concat(plusCharacter(this.checker.charAt(3))));
                return changeIntermediateCage(array, intermediateCage);
            }

            String intermediateCage = insertSpaces(plusCharacter(this.checker.charAt(2))
                    .concat(minusCharacter(this.checker.charAt(3))));
            return changeIntermediateCage(array, intermediateCage);

        } else if (characterChecker - 2 == characterStepChecker) {

            if (this.checker.charAt(3) + 1 == this.stepChecker.charAt(2) - 1) {
                String intermediateCage = insertSpaces(plusCharacter(this.checker.charAt(2))
                        .concat(minusCharacter(this.checker.charAt(3))));
                return changeIntermediateCage(array, intermediateCage);
            }

            String intermediateCage = insertSpaces(minusCharacter(this.checker.charAt(2))
                    .concat(plusCharacter(this.checker.charAt(3))));
            return changeIntermediateCage(array, intermediateCage);

        } else throw new Exception("Not the possibility of a move");
    }

    private String[][] changeIntermediateCage(String[][] array, String intermediateCage) throws Exception {
        FindIndexTuple2 intermediateCageIndex = new FindIndexTuple2(array, intermediateCage);

        if (intermediateCageIndex.getIndexI() != 0) {
            throw new Exception("The intermediate cell is free. Move is not possible ");
        } else {
            if (findMatch(array, this.checker.charAt(0), intermediateCage)) {
                throw new Exception("Checkers of the same color are not taken");
            } else {
                var field = changeField(array);

                String character = 'W' == this.checker.charAt(0) ? "B" : "W";
                String middle = String.format("%s-%s", character, intermediateCage.trim());
                FindIndexTuple2 findIndexTuple = new FindIndexTuple2(array, middle);
                field[findIndexTuple.getIndexI()][findIndexTuple.getIndexJ()] = intermediateCage;

                return field;
            }
        }
    }

    private String[][] changeField(String [][] array) {
        array[this.currentCheckerIndexI][this.currentCheckerIndexJ] = String.format(" %s ", this.checker.substring(2));
        array[this.stepCheckerIndexI][this.stepCheckerIndexJ] = String.format(
                "%s%s", this.checker.substring(0,2), this.stepChecker.trim());
        return array;
    }

    private String plusCharacter(char c) {
        char character = (char) (c + 1);
        return String.valueOf(character);
    }

    private String minusCharacter(char c) {
        char character = (char) (c - 1);
        return String.valueOf(character);
    }

    private boolean findMatch(String[][] array, char charChecker, String intermediateCage) {
        String character = String.valueOf(charChecker);
        String str = String.format("%s-%s", character, intermediateCage.trim());
        for (String[] strings : array) {
            for (int j = 0; j < array.length; j++) {
                if (str.equals(strings[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean equalCharactersVertical(String checker, String stepChecker) {
        return checker.regionMatches(2, stepChecker, 1, 1);
    }

    private boolean equalNumbersHorizontal(String checker, String stepChecker) {
        return checker.regionMatches(3, stepChecker, 2, 1);
    }

    static class FindIndexTuple2 {
        private int indexI = 0;
        private int indexJ = 0;

        FindIndexTuple2(String[][] array, String str) {
            index(array, str);
        }

        public int getIndexI() {
            return this.indexI;
        }

        public int getIndexJ() {
            return this.indexJ;
        }

        private void index(String[][] array, String str) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    if (array[i][j].equals(str)) {
                        this.indexI = i;
                        this.indexJ = j;
                    }
                }
            }
        }
    }
}
