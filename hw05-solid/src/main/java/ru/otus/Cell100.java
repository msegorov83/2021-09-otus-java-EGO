package ru.otus;

import java.util.ArrayList;

public class Cell100 implements BillAcceptor{

    private ArrayList cell100 = new ArrayList<Integer>();

    @Override
    public void addCashToCell(int i) {
        cell100.add(i);
    }

    @Override
    public int removeCashFromCell(final int count) {

        int cash=0;
        for (int i = 0; i < count; i++) {
            if (cell100.stream().count() != 0) {
                Integer elem = (Integer) cell100.stream().findFirst().get();
                cash = cash + elem;
                cell100.remove(0);
            } else {
                return cash;
            }
        }

        return cash;
    }

    @Override
    public int getBalanceFromCell() {
        return cell100.stream().mapToInt(a -> (int) a).sum();

    }
}
