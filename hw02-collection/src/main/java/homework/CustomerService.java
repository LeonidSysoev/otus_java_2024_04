package homework;

import java.util.*;


public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> map;

    public CustomerService() {
        map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }


    public Map.Entry<Customer, String> getSmallest() {
        return Map.entry(new Customer(map.firstEntry().getKey()), map.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var higher = map.higherEntry(customer);
        if (higher != null) {
            return Map.entry(new Customer(higher.getKey()), higher.getValue());
        } else {
            return null;
        }
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
