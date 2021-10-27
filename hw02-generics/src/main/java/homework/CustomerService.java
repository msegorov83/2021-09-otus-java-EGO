package homework;


import java.util.*;
import java.util.stream.Stream;


public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
      SortedMap<Customer, String> sortedMap = new TreeMap<>(Comparator.comparing(customer -> customer.getScores()));

    public Map.Entry<Customer, String> getSmallest() {

        return sortedMap.entrySet().stream().findFirst().get();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        TreeMap<Long, Map.Entry<Customer, String> > treeMap = new TreeMap<>();
        for (Map.Entry<Customer, String> e: sortedMap.entrySet()) {
            treeMap.put(e.getKey().getScores(),e);
        }

        try {
            return treeMap.ceilingEntry(customer.getScores()).getValue();
        }catch (Exception err) {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        Customer buff = new Customer(customer);
        sortedMap.put(buff, data);
    }
}
