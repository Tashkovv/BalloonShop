package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> placeOrder(String balloonColor, String ballonSize, String username);
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUsername(String username);
}
