package org.BYolivia.entities;

import jakarta.persistence.*;

import java.util.List;
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

    @Override
    public String toString() {
        return "AirplaneType{id=" + id + ", name='" + name + "', size=" + size + '}';
    }

    public static String toTable(List<AirplaneType> types) {
        String[] headers = {"ID", "Name", "Size"};
        int[] widths = {headers[0].length(), headers[1].length(), headers[2].length()};
        for (AirplaneType t : types) {
            widths[0] = Math.max(widths[0], String.valueOf(t.id).length());
            widths[1] = Math.max(widths[1], (t.name != null ? t.name : "null").length());
            widths[2] = Math.max(widths[2], String.valueOf(t.size).length());
        }
        String format = "| %-" + widths[0] + "s | %-" + widths[1] + "s | %-" + widths[2] + "s |%n";
        String separator = "+" + "-".repeat(widths[0] + 2) + "+" + "-".repeat(widths[1] + 2) + "+" + "-".repeat(widths[2] + 2) + "+";
        StringBuilder sb = new StringBuilder();
        sb.append(separator).append("\n");
        sb.append(String.format(format, headers[0], headers[1], headers[2]));
        sb.append(separator).append("\n");
        for (AirplaneType t : types) {
            sb.append(String.format(format, t.id, t.name, t.size));
        }
        sb.append(separator);
        return sb.toString();
    }
}