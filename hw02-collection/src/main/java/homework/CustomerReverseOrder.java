package homework;

import java.util.ArrayDeque;
import java.util.Deque;



public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final Deque<Customer> customerDeque;

    public CustomerReverseOrder() {
        customerDeque = new ArrayDeque<>();
    }


    public void add(Customer customer) {
        customerDeque.offerLast(customer);
    }

    public Customer take() {
        return customerDeque.removeLast(); // это "заглушка, чтобы скомилировать"
    }
}
