package com.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalCheckers {
    public static void main(String[] args) throws IOException {
        char[][] field = {
                {' ', 'P', ' ', 'P', ' ', 'P', ' ', 'P'},
                {'P', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {' ', 'P', ' ', 'P', ' ', 'P', ' ', 'P'},
                {'P', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {' ', 'P', ' ', 'P', ' ', 'P', ' ', 'P'},
                {'P', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {' ', 'P', ' ', 'P', ' ', 'P', ' ', 'P'},
                {'P', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
        };

        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        String command = bufReader.readLine();

        if (command.equals("start")) {

            while (true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferedReader.readLine();

                if (s.equals("step")) {
                    for (char[] i : field) {
                        for (char j : i) {
                            System.out.print(j);
                        }
                        System.out.println();
                    }
                } else if (s.equals("end")) break;
            }
        }
    }
}
