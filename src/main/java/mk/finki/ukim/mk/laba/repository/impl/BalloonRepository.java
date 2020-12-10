package mk.finki.ukim.mk.laba.repository.impl;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    public final List<Balloon> balloons = new ArrayList<>() {
        /*{
            add(new Balloon("Red", "Red Balloon"));
            add(new Balloon("Green", "Green Balloon"));
            add(new Balloon("Yellow", "Yellow Balloon"));
            add(new Balloon("Blue", "Blue Balloon"));
            add(new Balloon("Black", "Black Balloon"));
            add(new Balloon("Orange", "Orange Balloon"));
            add(new Balloon("White", "White Balloon"));
            add(new Balloon("Pink", "Pink Balloon"));
            add(new Balloon("Brown", "Brown Balloon"));
            add(new Balloon("Golden", "Golden Balloon"));
        }*/
    };

    public List<Balloon> findAllBalloons() {
        balloons.sort(Balloon::compareTo);
        return balloons;
    }

    public List<Balloon> findByType(String text) {
        return balloons.stream().filter(r -> r.getBalloonType() == TYPE.valueOf(text)).collect(Collectors.toList());
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloons.stream()
                .filter(r -> r.getName().contains(text) || r.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Optional<Balloon> saveOrUpdate(String name, String description, Manufacturer manufacturer, TYPE balloonType){
        balloons.removeIf(r -> r.getName().equals(name) && r.getDescription().equals(description));
        Balloon b = new Balloon(name, description, manufacturer, balloonType);
        balloons.add(b);
        return Optional.of(b);
    }

    public void deleteById(Long id) {
        balloons.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return balloons.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
