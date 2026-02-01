package org.BYolivia.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "AIRPLANETYPE")
public class AirplaneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "size")
    private Double size;

    @OneToMany(mappedBy = "fk_airplanetype",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Airplane> airplanes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Set<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Set<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        AirplaneType that = (AirplaneType) o;
        if (id != null && that.id != null) {
            return Objects.equals(id, that.id);
        } else {
            return Objects.equals(name, that.name) && Objects.equals(size, that.size);
        }
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return (name == null && size == null) ? 0 : Objects.hash(name, size);
    }
}