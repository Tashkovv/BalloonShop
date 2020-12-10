package mk.finki.ukim.mk.laba.repository.jpa;

import mk.finki.ukim.mk.laba.model.Balloon;
import mk.finki.ukim.mk.laba.model.enums.TYPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepositoryJpa extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByNameOrDescriptionLike(String name, String description);
    void deleteByName(String name);
    List<Balloon> findAllByBalloonType(TYPE balloonType);
}
