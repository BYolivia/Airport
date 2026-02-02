package org.BYolivia.entities;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "AIRPORT")
public class Airport {
    public Airport(){}

    public Airport(Integer capacity) {
        this.capacity = capacity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "capacity")
    private Integer capacity;

    @ManyToMany
    @JoinTable(
        name = "neighbour",
        joinColumns = @JoinColumn(name = "neighbour"),
        inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Airport> neighbour; // Aeropuertos vecinos

    @OneToMany(mappedBy = "fk_airport",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Airplane> airplanes;

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }


    public Set<Airport> getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(Set<Airport> neighbour) {
        this.neighbour = neighbour;
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

        Airport airport = (Airport) o;
        if (id != null && airport.id != null) {
            return Objects.equals(id, airport.id);
        } else {
            return Objects.equals(capacity, airport.capacity) && Objects.equals(neighbour, airport.neighbour);
        }
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return (capacity == null && neighbour == null) ? 0 : Objects.hash(capacity, neighbour);
    }
}