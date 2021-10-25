package homework;


import java.util.*;
import java.util.stream.Stream;


public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    Map<Customer, String> map = new HashMap<>();


    public Map.Entry<Customer, String> getSmallest() {

        Map<Customer,String>sorted = new LinkedHashMap<>();
        Stream<Map.Entry<Customer,String>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getKey().getScores()))
                .forEach(e ->sorted.put(e.getKey(),e.getValue()));

        return  sorted.entrySet().stream().findFirst().get();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {

        TreeMap<Long, Map.Entry<Customer, String> > treeMap = new TreeMap<>();
        for (Map.Entry<Customer, String> e: map.entrySet()) {
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
        map.put(buff, data);
    }
}
