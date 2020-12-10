package mk.finki.ukim.mk.laba.service.impl;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.Manufacturer;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import mk.finki.ukim.mk.laba.model.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.mk.laba.repository.impl.BalloonRepository;
import mk.finki.ukim.mk.laba.repository.impl.ManufacturerRepository;
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
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescriptionLike(text, text);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Balloon> SaveOrUpdate(String name, String description, Long manufacturerId, TYPE balloonType) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        balloonRepository.deleteByName(name);
        return Optional.of(balloonRepository.save(new Balloon(name, description, manufacturer, balloonType)));
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public List<Balloon> searchByType(String type) {
        return balloonRepository.findAllByBalloonType(TYPE.valueOf(type));
    }
}