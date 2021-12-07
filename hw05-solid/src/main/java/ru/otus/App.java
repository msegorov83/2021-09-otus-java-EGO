package ru.otus;

import java.util.Scanner;

public class App {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        ATM atm = new ATM();

        while (true) {
        printMainMenu();
        int pressedKey = scanner.nextInt();

            switch (pressedKey){
               case 1:
                   System.out.println("Balance: " + atm.getBalance());
                   break;
               case 2:
                   printDepositMenu();
                   int denomination = scanner.nextInt();
                   atm.putCash(denomination);
                   break;
               case 3:
                   System.out.print("Enter amount money:");
                   int amountMoney = scanner.nextInt();
                   atm.getCash(amountMoney);
                   break;
               case 4: System.exit(0);
            }
        }

    }

    public static void printMainMenu() {
        System.out.println("Press key:");
        System.out.println("1 - get balance");
        System.out.println("2 - deposit money into an ATM");
        System.out.println("3 - withdraw money from an ATM");
        System.out.println("4 - eixt");
    }

    public static void printDepositMenu() {
        System.out.println("Insert a bill:");
        System.out.println("100 RUB");
        System.out.println("500 RUB");
        System.out.println("1000 RUB");
    }
    
}

