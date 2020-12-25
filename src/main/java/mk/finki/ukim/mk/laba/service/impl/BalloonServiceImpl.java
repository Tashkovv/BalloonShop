package mk.finki.ukim.mk.laba.service.impl;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import mk.finki.ukim.mk.laba.model.exceptions.BalloonNotFoundException;
import mk.finki.ukim.mk.laba.model.exceptions.ManufacturerIdNotFoundException;
import mk.finki.ukim.mk.laba.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.laba.repository.jpa.BalloonRepositoryJpa;
import mk.finki.ukim.mk.laba.repository.jpa.ManufacturerRepositoryJpa;
import mk.finki.ukim.mk.laba.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {
    public final BalloonRepositoryJpa balloonRepository;
    public final ManufacturerRepositoryJpa manufacturerRepository;

    public BalloonServiceImpl(BalloonRepositoryJpa balloonRepository, ManufacturerRepositoryJpa manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescriptionLike(String text, String by) {
        if (by.equals("name"))
            return balloonRepository.findAllByNameLike(text);
        else return balloonRepository.findAllByDescriptionLike(text);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> SaveOrUpdate(String name, String description, Long manufacturerId, TYPE balloonType) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerIdNotFoundException(manufacturerId));
        balloonRepository.deleteByName(name);
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer, balloonType)));
    }

    @Override
    @Transactional
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturerId, TYPE balloonType) {
        Balloon balloon = balloonRepository.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));

        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerIdNotFoundException(manufacturerId));

        balloon.setBalloonType(balloonType);
        balloon.setDescription(description);
        balloon.setManufacturer(manufacturer);
        balloon.setName(name);

        return Optional.of(balloonRepository.save(balloon));
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public List<Balloon> searchByType(String type) {
        return balloonRepository.findAllByBalloonType(TYPE.valueOf(type));
    }
    @Override
    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer, TYPE balloonType) {
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer, balloonType)));
    }
}
