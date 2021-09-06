package com.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

public class TerminalCheckers {
    private static final String standardCommandStart = "start";
    private static final String standardCommandRules = "rules";
    private static final String standardCommandEndGame = "end game";
    private static final String standardCommandCommands = "commands";
    private static final String standardCommandGetMenu = "get menu";
    static String[][] field;
    static String command;

    static {
        try {
            field = new CreateField(new String[17][17]).getField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> commands = new ArrayList<>();
        Collections.addAll(
                commands, standardCommandCommands, standardCommandStart, standardCommandRules, standardCommandEndGame
        );

        System.out.println("----Terminal checkers----\n");
        System.out.printf("""
                %s --> start game
                %s --> description game rules
                %s --> all commands game
                %s --> get out this game
                %n""", standardCommandStart, standardCommandRules, standardCommandCommands, standardCommandEndGame);

        command = teamCheck(commands);

        switch (command) {
            case standardCommandEndGame -> System.out.println("You out this game :(");
            case standardCommandRules -> startRulesGame();
            case standardCommandStart -> startGame(command);
            case standardCommandCommands -> commandsGame();
        }
    }

    static String teamCheck(ArrayList<String> array) throws IOException {
        String string;
        int teamMatch = 0;

        do {
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
            string = buffReader.readLine();

            for (String str : array) {
                if (string.equals(str)) {
                    teamMatch++;
                }
            }

            if (teamMatch == 0) {
                System.out.println("Command is not found, try again...");
            }

        } while (teamMatch == 0);

        return string;
    }

    static void startGame(String gameCommand) throws IOException {
        while (true) {

            if (gameCommand.equals(standardCommandEndGame)) {
                System.out.println("Game over");
                break;
            }

            if (gameCommand.equals(standardCommandGetMenu)) {
                TerminalCheckers.main(new String[1]);
            }

            for (String[] i : field) {
                for (String j : i) {
                    System.out.print(j);
                }
                System.out.println();
            }

            boolean tryAgain;
            do {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                gameCommand = bufferedReader.readLine();

                if (validateCommand(gameCommand.substring(0, 3), gameCommand.substring(4))) {
                    try {
                        field = new UpdateField(field, gameCommand).getField();
                    } catch (Exception e) {
                        System.out.println("Try again");
                        tryAgain = true;
                    }
                    tryAgain = false;
                } else if (validateStandardCommand(gameCommand)) {
                    tryAgain = false;
                } else {
                    System.out.println("Command not validated, try again...");
                    tryAgain = true;
                }
            } while (tryAgain);
        }
    }

    static void startRulesGame() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String gameCommand = bufferedReader.readLine();

        if (gameCommand.equals(standardCommandStart)) startGame(gameCommand);
    }

    static boolean validateCommand(String checker, String stepChecker) {
        // example Ba4 b5, Wb7 c6
        return Pattern.matches("\\A[W-|B-][a-h][1-8]\\z", checker) && Pattern.matches("\\A[a-h][1-8]\\z", stepChecker);
    }

    static boolean validateStandardCommand(String command) {
        return Objects.equals(command, standardCommandEndGame) || Objects.equals(command, standardCommandGetMenu);
    }

    static void commandsGame() throws IOException {
        System.out.printf("""
                %s --> start game
                %s --> get out this game
                %s --> get out in menu
                %s --> description game rules
                                
                get menu --> get out in menu
                """, standardCommandStart, standardCommandEndGame, standardCommandGetMenu, standardCommandRules);

        do {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String gameCommand = bufferedReader.readLine();

            if (gameCommand.equals(standardCommandGetMenu)) {
                TerminalCheckers.main(new String[1]);
                break;
            } else {
                System.out.println("Repeat you command");
            }
        } while (true);

    }
}
