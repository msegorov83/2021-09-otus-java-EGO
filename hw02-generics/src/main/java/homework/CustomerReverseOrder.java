package homework;

import java.util.*;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    LinkedHashSet<Customer> set = new LinkedHashSet <>();
    int currentInd = 0;

    public void add(Customer customer) {
        set.add(customer);
    }

    public Customer take() {

        List<Customer> list = new ArrayList<>(set);
        Collections.reverse(list);
        Customer currCustomer = list.get(currentInd);
        currentInd++;

        return currCustomer;
    }

}
