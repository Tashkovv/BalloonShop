package mk.finki.ukim.mk.laba.service.impl;

import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.repository.impl.ManufacturerRepository;
import mk.finki.ukim.mk.laba.repository.jpa.ManufacturerRepositoryJpa;
import mk.finki.ukim.mk.laba.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepositoryJpa manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepositoryJpa manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address, String country) {
        return Optional.of(manufacturerRepository.save(new Manufacturer(name, country, address)));
    }
}
