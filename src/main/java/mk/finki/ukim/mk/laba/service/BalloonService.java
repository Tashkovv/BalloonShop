package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.enums.TYPE;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();

    Optional<Balloon> findById(Long id);

    List<Balloon> searchByNameOrDescriptionLike(String text,String by);

    Optional<Balloon> SaveOrUpdate(String name, String description, Long manufacturerId, TYPE balloonType, String oldName);

    void deleteById(Long id);

    List<Balloon> searchByTypeLike(String type);

    List<Balloon> searchByManufacturerLike(String text);

    List<Balloon> search(String text, String by);
}
