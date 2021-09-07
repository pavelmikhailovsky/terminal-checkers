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
        this.currentCheckerIndexI = findIndex(array, this.checker).getIndexI();
        this.currentCheckerIndexJ = findIndex(array, this.checker).getIndexJ();
        this.stepCheckerIndexI = findIndex(array, this.stepChecker).getIndexI();
        this.stepCheckerIndexJ = findIndex(array, this.stepChecker).getIndexJ();
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
        int characterChecker = checker.charAt(2);
        int characterStepChecker = stepChecker.charAt(1);

        if (characterChecker + 1 == characterStepChecker || characterChecker - 1 == characterStepChecker) {
            array[this.currentCheckerIndexI][this.currentCheckerIndexJ] = String.format(" %s ", this.checker.substring(2));
            array[this.stepCheckerIndexI][this.stepCheckerIndexJ] = String.format(
                    "%s%s", this.checker.substring(0,2), this.stepChecker.trim());
            return array;
        } else if (characterChecker + 2 == characterStepChecker || characterChecker - 2 == characterStepChecker) {

        } else throw new Exception("Not the possibility of a move");

        return new String[17][17];
    }

    private boolean equalCharactersVertical(String checker, String stepChecker) {
        return checker.regionMatches(2, stepChecker, 1, 1);
    }

    private boolean equalNumbersHorizontal(String checker, String stepChecker) {
        return checker.regionMatches(3, stepChecker, 2, 1);
    }

    private FindIndexTuple2 findIndex(String[][] array, String str) {
        return new FindIndexTuple2(array, str);
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
