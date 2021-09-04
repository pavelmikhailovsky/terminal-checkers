package com.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalCheckers {
    static String[][] field;

    static {
        try {
            field = new CreateField(new String[17][17]).getField();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("----Start game -> 'start'----");
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        String command = buffReader.readLine();

        if (command.equals("start")) {
            while (true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferedReader.readLine();

                if (s.equals("step")) {
                    for (String[] i : field) {
                        for (String j : i) {
                            System.out.print(j);
                        }
                        System.out.println();
                    }
                } else if (s.equals("end")) break;
            }
        }
    }
}
