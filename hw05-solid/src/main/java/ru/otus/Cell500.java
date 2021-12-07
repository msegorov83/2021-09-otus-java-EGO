package ru.otus;

import java.util.ArrayList;

public class Cell500 implements BillAcceptor{

    private ArrayList cell500 = new ArrayList<Integer>();

    @Override
    public void addCashToCell(int i) {
        cell500.add(i);
    }

    @Override
    public int removeCashFromCell(final int count) {
        int cash=0;
        for (int i = 0; i < count; i++) {
            if (cell500.stream().count() != 0) {
                Integer elem = (Integer) cell500.stream().findFirst().get();
                cash = cash + elem;
                cell500.remove(0);
            } else {
                return cash;
            }
        }

        return cash;
    }

    @Override
    public int getBalanceFromCell() {
        return cell500.stream().mapToInt(a -> (int) a).sum();
    }
}
