package de.htwg.se.nmm;

import java.util.Scanner;
import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.aview.tui.TextUI;

public class Game {

    static Scanner scanner;

    private Game() {}

    public static void main(String[] args) {
        TextUI textUI = new TextUI(new GameController(new Board()));
        textUI.printTUI();

        boolean game = true; // quit on -q
        scanner = new Scanner(System.in);
        while (game) {
            game = textUI.processInputLine(scanner.next());
        }
    }
}