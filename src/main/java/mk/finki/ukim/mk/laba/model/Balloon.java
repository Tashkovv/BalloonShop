package mk.finki.ukim.mk.laba.model;

import lombok.Data;
import mk.finki.ukim.mk.laba.model.enums.TYPE;

import javax.persistence.*;

@Data
@Entity
public class Balloon implements Comparable<Balloon> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    private TYPE balloonType;

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Balloon() {
    }

    public Balloon(String name, String description, Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon(String name, String description, Manufacturer manufacturer, TYPE balloonType) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.balloonType = balloonType;
    }


    @Override
    public int compareTo(Balloon o) {
        return this.getName().compareTo(o.getName());
    }

}
