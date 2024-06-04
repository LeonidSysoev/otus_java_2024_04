package homework;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparing(Customer::getScores));


    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallest = null;
        for (Map.Entry<Customer, String> customer : map.entrySet()) {
            if (smallest == null || smallest.getKey().getScores() > customer.getKey().getScores()) {
                smallest = customer;
            }
        }
        map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(Customer::getScores)));

        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return smallest; // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return null; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
