package mk.finki.ukim.mk.laba.repository.impl;

import mk.finki.ukim.mk.laba.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {
    public final List<Manufacturer> manufacturers = new ArrayList<>();

    /*@PostConstruct
    public void init() {
        manufacturers.add(new Manufacturer("Microsoft", "USA", "US US"));
        manufacturers.add(new Manufacturer("Apple", "USA", "LA LA"));
        manufacturers.add(new Manufacturer("Nike", "USA", "NY NY"));
        manufacturers.add(new Manufacturer("Volkswagen", "GER", "GE GE"));
        manufacturers.add(new Manufacturer("Converse", "USA", "MA MA"));
    }*/

    public List<Manufacturer> findAll() {
        return manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return manufacturers.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
