package mk.finki.ukim.mk.laba.repository.jpa;

import mk.finki.ukim.mk.laba.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepositoryJpa extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByNameLike(String name);
}
