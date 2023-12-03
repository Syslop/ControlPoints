package ru.geekbrains.lesson6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallParadox {
    static Random random;
    static Map<Integer, Boolean> results1;       // Статистика для игрока, не меняющего свой выбор.
    static Map<Integer, Boolean> results2;       // Статистика для игрока, изменяющего свой выбор.
    static int doorsNumber;                      // Количество дверей.
    static int attempts;                         // Количество попыток.

    public static void main(String[] args) {
        random = new Random();
        results1 = new HashMap<>();
        results2 = new HashMap<>();
        doorsNumber = 3;
        attempts = 1000;

        for (int i = 0; i < attempts; i++) {     // Розыгрыш (1000 попыток).
            playRound(i);
        }

        int winner = 0;                             // Статистика для первого игрока, не меняющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry : results1.entrySet()) {
            if (entry.getValue()) {
                winner++;
            }
        }
        System.out.println("\nПарадокс Монти Холла");
        System.out.println("\nСтатистика выигрышей для игрока, не меняющего свой выбор:");
        System.out.println("Количество побед: " + winner + " раз из " + attempts + " попыток.");

        winner = 0;                                  // Статистика для второго игрока, изменяющего свой выбор.
        for (Map.Entry<Integer, Boolean> entry : results2.entrySet()) {
            if (entry.getValue()) {
                winner++;
            }
        }
        System.out.println("\nСтатистика выигрышей для игрока, изменяющего свой выбор:");
        System.out.println("Количество побед: " + winner + " раз из " + attempts + " попыток.");
    }

    private static void playRound(int roundNumber) {
        int winningDoor = random.nextInt(doorsNumber);
        int firstChoice = random.nextInt(doorsNumber);
        int openDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < doorsNumber; i++) {
            if (i != winningDoor && i != firstChoice) {
                openDoor = i;
            }
        }

        for (int i = 0; i < doorsNumber; i++) {            // Игрок не изменяет свой выбор.
            if (i != openDoor && i != firstChoice) {
                secondChoice = firstChoice;
            }
        }
        results1.put(roundNumber, winningDoor == secondChoice);   // Статистика для первого игрока.

        for (int i = 0; i < doorsNumber; i++) {            // Игрок изменяет свой выбор.
            if (i != openDoor && i != firstChoice) {
                secondChoice = i;
            }
        }
        results2.put(roundNumber, winningDoor == secondChoice);   // Статистика для второго игрока.
    }
}
