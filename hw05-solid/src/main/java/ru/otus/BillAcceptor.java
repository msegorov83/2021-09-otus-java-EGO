package ru.otus;

public interface BillAcceptor {
    void addCashToCell(int i);
    int removeCashFromCell(int count);
    int getBalanceFromCell();
}
