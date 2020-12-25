package mk.finki.ukim.mk.laba.service;

import mk.finki.ukim.mk.laba.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    public List<Manufacturer> findAll();
    public Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> save(String name, String address, String country);
}
