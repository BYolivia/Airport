package org.BYolivia.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<Airport> neighbour = new HashSet<>(); // Aeropuertos vecinos

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

    public void addNeighbour(Airport airport) {
        if (neighbour == null) {
            neighbour = new HashSet<>();
        }
        this.neighbour.add(airport);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;
        if (id != null && airport.id != null) {
            return Objects.equals(id, airport.id);
        } else {
            return Objects.equals(capacity, airport.capacity);
        }
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return capacity != null ? capacity.hashCode() : 0;
    }

    @Override
    public String toString() {
        String neighbours = (neighbour != null && !neighbour.isEmpty())
                ? neighbour.stream()
                    .map(a -> "Airport{id=" + a.getId() + ", capacity=" + a.getCapacity() + "}")
                    .collect(Collectors.joining(", "))
                : "None";
        return "Airport{id=" + id + ", capacity=" + capacity + ", neighbours=[" + neighbours + "]}";
    }

    public static String toTable(List<Airport> airports) {
        String[] headers = {"ID", "Capacity", "Neighbours"};
        int[] widths = {headers[0].length(), headers[1].length(), headers[2].length()};
        String[] neighbourStrs = new String[airports.size()];
        for (int i = 0; i < airports.size(); i++) {
            Airport a = airports.get(i);
            Set<Airport> nb = a.getNeighbour();
            neighbourStrs[i] = (nb != null && !nb.isEmpty())
                    ? nb.stream().map(n -> String.valueOf(n.getId())).collect(Collectors.joining(", "))
                    : "None";
            widths[0] = Math.max(widths[0], String.valueOf(a.id).length());
            widths[1] = Math.max(widths[1], String.valueOf(a.capacity).length());
            widths[2] = Math.max(widths[2], neighbourStrs[i].length());
        }
        String format = "| %-" + widths[0] + "s | %-" + widths[1] + "s | %-" + widths[2] + "s |%n";
        String separator = "+" + "-".repeat(widths[0] + 2) + "+" + "-".repeat(widths[1] + 2) + "+" + "-".repeat(widths[2] + 2) + "+";
        StringBuilder sb = new StringBuilder();
        sb.append(separator).append("\n");
        sb.append(String.format(format, headers[0], headers[1], headers[2]));
        sb.append(separator).append("\n");
        for (int i = 0; i < airports.size(); i++) {
            Airport a = airports.get(i);
            sb.append(String.format(format, a.id, a.capacity, neighbourStrs[i]));
        }
        sb.append(separator);
        return sb.toString();
    }
}