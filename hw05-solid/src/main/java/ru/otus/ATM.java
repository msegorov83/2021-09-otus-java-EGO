package ru.otus;

public class ATM {
    private Cell100 cell100;
    private Cell500 cell500;
    private Cell1000 cell1000;

    public ATM() {
        this.cell100 = new Cell100();
        this.cell500 = new Cell500();
        this.cell1000 = new Cell1000();
        this.putDefaultCash();
    }

    private void putDefaultCash() {
        cell100.addCashToCell(100);
        cell500.addCashToCell(500);
        cell1000.addCashToCell(1000);
    }

    public int getBalance() {
        return cell100.getBalanceFromCell()+cell500.getBalanceFromCell()+cell1000.getBalanceFromCell();
    }

    public void putCash(final int denomination) {
        switch(denomination){
            case 100: cell100.addCashToCell(100); break;
            case 500: cell500.addCashToCell(500); break;
            case 1000: cell1000.addCashToCell(1000); break;
            default: System.out.println("Failed to recognize the bill");
        }
    }

    public int getCash(final int amountMoney) {
        int cash = 0;

        if (this.getBalance() < amountMoney) {
            System.out.println("Not enough money");
            return 0;
        }

        if ((amountMoney  % 100) != 0) {
            System.out.println("Enter the amount in multiples of 100");
            return 0;
        }

        while (cash != amountMoney)
        {
            int count1000 = Math.abs(amountMoney/1000);
            cash = cash + cell1000.removeCashFromCell(count1000);

            int count500 = Math.abs((amountMoney-cash)/500);
            cash = cash + cell500.removeCashFromCell(count500);

            int count100 = Math.abs((amountMoney-cash)/100);
            cash = cash + cell100.removeCashFromCell(count100);
        }

        return cash;
    }

}
