package homework;

import java.util.*;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    LinkedHashMap<Long,Customer>map = new LinkedHashMap<>();
    Map<Long,Customer> reverseOrderedMap = new LinkedHashMap<>();

    public void add(Customer customer) {
        map.put(customer.getId(), customer);
        reverseOrderedMap=reverseMap(map);
    }

    public Customer take() {
        Customer currCustomer = reverseOrderedMap.entrySet().stream().findFirst().get().getValue();
        reverseOrderedMap.remove(currCustomer.getId());
        return currCustomer;
    }

    private static <K, V> LinkedHashMap<K, V> reverseMap(LinkedHashMap<K, V> map)
    {
        LinkedHashMap<K, V> reversedMap = new LinkedHashMap<>();
        List<K> reverseOrderedKeys = new ArrayList<>(map.keySet());
        Collections.reverse(reverseOrderedKeys);
        reverseOrderedKeys.forEach((key)->reversedMap.put(key,map.get(key)));
        return reversedMap;
    }
}
