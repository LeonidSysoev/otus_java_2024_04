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
        var smallest = Map.Entry.comparingByKey(Comparator.comparing(Customer::getScores));
//        for (Map.Entry<Customer, String> customer : map.entrySet()) {
//            if (smallest == null || smallest.getKey().getScores() > customer.getKey().getScores()) {
//                smallest = customer;
//            }
//        }

        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return null;
                //Map.entry(new Customer(map.firstEntry().getKey()), map.firstEntry().getValue());  // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return null; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
