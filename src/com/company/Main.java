package com.company;

import java.util.Random;

public class Main {

    public static int[] health = {3000, 400, 250, 250, 550, 300, 450, 300, 250};
    public static int[] hits = {50, 35, 20, 20, 10, 20, 30, 25, 10};
    public static String[] hitTypes = {"Physical", "Physical",
            "Magical", "Mental", "Shield", "Miss", "Berserk", "Stun", "Healing"};


    public static void main(String[] args) {
        printStatistic();
        while (!isFinished()) {
            sStun();
            changeBossDefence();
            round();
            printStatistic();

        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(8) + 1;
        hitTypes[0] = hitTypes[randomNumber];

    }


    public static int playerHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            Random r = new Random();
            int randomNumber = r.nextInt(7) + 2;
            System.out.println(hitTypes[playerIndex]
                    + " critically hits Boss " +
                    hits[playerIndex] * randomNumber);
            return health[0] - hits[playerIndex] * randomNumber;
        } else {
            return health[0] - hits[playerIndex];

        }
    }

    public static int bossHit(int playerIndex) {

        return health[playerIndex] - hits[0];
    }


    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won the game!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0 &&
                health[5] <= 0 && health[6] <= 0 &&
                health[7] <= 0 && health[8] <= 0) {
            System.out.println("Boss won the game!!!");
            return true;
        }
        return false;
    }

    public static void printStatistic() {
        System.out.println("_________________");
        System.out.println("Boss health: " + health[0]);
        System.out.println("Boss defence: " + hitTypes[0]);
        System.out.println("Warrior health: " + health[1]);
        System.out.println("Magic health: " + health[2]);
        System.out.println("Mental health: " + health[3]);
        System.out.println("Tank health:" + health[4]);
        System.out.println("Nimble health:" + health[5]);
        System.out.println("Berserk health:" + health[6]);
        System.out.println("Thor health:" + health[7]);
        System.out.println("Healer health:" + health[8]);
        System.out.println("_________________");
    }


    public static int healingPlayer(int indexPlayer) {


        if (health[indexPlayer] > 0 && health[8] > 0) {
            return hits[8] + health[indexPlayer];
        }

        return health[indexPlayer];
    }


    public static void sShield() {
        Random r = new Random();
        int randomPlayer = r.nextInt(8) + 1;

        Random ra = new Random();
        int randomShield = ra.nextInt(50);

        System.out.println("Shield " + " hero " + randomPlayer + " damage");
        System.out.println("____________________");
        if (health[4] > 0) {
            health[randomPlayer] = health[randomPlayer] += randomShield;
            health[4] = health[4] - randomShield;
        } else if (health[4] <= 0) {
            System.out.println("---Tank has Died---");

        }
    }

    public static void sStun() {
        Random ra = new Random();
        int randomM = ra.nextInt(3);
        if (randomM == 1) {
            System.out.println("Thor stunned the Boss");
            System.out.println("_____________________");
            hits[0] = 0;

        } else {
            hits[0] = 50;
        }
    }


    public static void bBerserk() {
        Random ra = new Random();
        int randomM = ra.nextInt(50);
        System.out.println("Berserk return Hit " + randomM);
        System.out.println("___________________");
        health[0] = health[0] - (hits[6] + randomM);

    }


    public static void mMiss() {
        Random r = new Random();
        int randomN = r.nextInt(2);
        if (randomN == 1) {
            System.out.println("Boss miss Nimble");
            System.out.println("________________");
            health[5] = health[5] + hits[0];
        }

    }


    public static void round() {



        for (int i = 1; i <= 8; i++) {
            int healthRemain = bossHit(i);
            if (healthRemain < 0) {
                health[i] = 0;
            } else {
                health[i] = healthRemain;
            }
        }


        mMiss();
        sShield();
        bBerserk();


        for (int i = 1; i <= 7; i++) {
            int healthRemain = playerHit(i);
            if (healthRemain < 0) {
                health[0] = 0;
            } else {
                health[0] = healthRemain;
            }
        }


        for (int u = 1; u <= 7; u++) {
            health[u] = healingPlayer(u);

        }


    }

}



