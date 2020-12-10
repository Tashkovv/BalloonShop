package mk.finki.ukim.mk.laba.service.impl;

import mk.finki.ukim.mk.laba.model.Order;
import mk.finki.ukim.mk.laba.model.User;
import mk.finki.ukim.mk.laba.model.exceptions.UserNotFoundException;
import mk.finki.ukim.mk.laba.repository.impl.OrderRepository;
import mk.finki.ukim.mk.laba.repository.jpa.OrderRepositoryJpa;
import mk.finki.ukim.mk.laba.repository.jpa.UserRepository;
import mk.finki.ukim.mk.laba.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepositoryJpa orderRepository;

    public OrderServiceImpl(OrderRepositoryJpa orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, String username) {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> {
                    throw new UserNotFoundException(username);
                });
        return Optional.of(orderRepository.save(new Order(balloonColor, balloonSize, user)));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllOrdersByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> {
                    throw new UserNotFoundException(username);
                });
        return orderRepository.findAllByUser(user);
    }
}
