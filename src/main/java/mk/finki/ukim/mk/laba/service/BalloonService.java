package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.enums.TYPE;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();

    Optional<Balloon> findById(Long id);

    List<Balloon> searchByNameOrDescription(String text);

    Optional<Balloon> SaveOrUpdate(String name, String description, Long manufacturerId, TYPE balloonType);

    void deleteById(Long id);

    List<Balloon> searchByType(String type);
}
