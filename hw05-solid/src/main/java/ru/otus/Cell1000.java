package ru.otus;

import java.util.ArrayList;

public class Cell1000 implements BillAcceptor{

    private ArrayList cell1000 = new ArrayList<Integer>();

    @Override
    public void addCashToCell(int i) {
        cell1000.add(i);

    }

    @Override
    public int removeCashFromCell(final int count) {
        int cash=0;
        for (int i = 0; i < count; i++) {
            if (cell1000.stream().count() != 0) {
                Integer elem = (Integer) cell1000.stream().findFirst().get();
                cash = cash + elem;
                cell1000.remove(0);
            } else {
                return cash;
            }
        }

        return cash;
    }

    @Override
    public int getBalanceFromCell() {
        return cell1000.stream().mapToInt(a -> (int) a).sum();
    }
}
