package mk.finki.ukim.mk.laba.repository.jpa;

import mk.finki.ukim.mk.laba.model.Order;
import mk.finki.ukim.mk.laba.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryJpa extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User u);
}
