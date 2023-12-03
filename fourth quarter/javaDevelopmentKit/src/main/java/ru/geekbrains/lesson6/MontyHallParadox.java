package ru.geekbrains.lesson6;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontyHallParadox {
    static Random random;
    static Map<Integer, Boolean> results1;       // ���������� ��� ������, �� ��������� ���� �����.
    static Map<Integer, Boolean> results2;       // ���������� ��� ������, ����������� ���� �����.
    static int doorsNumber;                      // ���������� ������.
    static int attempts;                         // ���������� �������.

    public static void main(String[] args) {
        random = new Random();
        results1 = new HashMap<>();
        results2 = new HashMap<>();
        doorsNumber = 3;
        attempts = 1000;

        for (int i = 0; i < attempts; i++) {     // �������� (1000 �������).
            playRound(i);
        }

        int winner = 0;                             // ���������� ��� ������� ������, �� ��������� ���� �����.
        for (Map.Entry<Integer, Boolean> entry : results1.entrySet()) {
            if (entry.getValue()) {
                winner++;
            }
        }
        System.out.println("\n�������� ����� �����");
        System.out.println("\n���������� ��������� ��� ������, �� ��������� ���� �����:");
        System.out.println("���������� �����: " + winner + " ��� �� " + attempts + " �������.");

        winner = 0;                                  // ���������� ��� ������� ������, ����������� ���� �����.
        for (Map.Entry<Integer, Boolean> entry : results2.entrySet()) {
            if (entry.getValue()) {
                winner++;
            }
        }
        System.out.println("\n���������� ��������� ��� ������, ����������� ���� �����:");
        System.out.println("���������� �����: " + winner + " ��� �� " + attempts + " �������.");
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

        for (int i = 0; i < doorsNumber; i++) {            // ����� �� �������� ���� �����.
            if (i != openDoor && i != firstChoice) {
                secondChoice = firstChoice;
            }
        }
        results1.put(roundNumber, winningDoor == secondChoice);   // ���������� ��� ������� ������.

        for (int i = 0; i < doorsNumber; i++) {            // ����� �������� ���� �����.
            if (i != openDoor && i != firstChoice) {
                secondChoice = i;
            }
        }
        results2.put(roundNumber, winningDoor == secondChoice);   // ���������� ��� ������� ������.
    }
}
