package mk.finki.ukim.mk.laba.repository.impl;

import mk.finki.ukim.mk.laba.model.Order;
import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.model.exceptions.InvalidOrderException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
    List<Order> orderList = new ArrayList<>();
    private long id = 0;

    public Order placeOrder(String balloonColor, String balloonSize, String clientName, String clientAddress) {
        if (balloonColor == null || balloonColor.isEmpty() || balloonSize == null || balloonSize.isEmpty() || clientName == null || clientName.isEmpty() || clientAddress == null || clientAddress.isEmpty()) {
            throw new InvalidOrderException();
        }

        Order o = new Order(balloonColor, balloonSize, new User());
        id += 1;
        orderList.add(o);
        return o;

    }

    public List<Order> findAll() {
        return orderList;
    }

    public List<Order> findOrdersByNameAndAddress(String name) {
        return orderList.stream()
                .filter(r -> r.getUser().getUsername().equals(name))
                .collect(Collectors.toList());
    }
}
