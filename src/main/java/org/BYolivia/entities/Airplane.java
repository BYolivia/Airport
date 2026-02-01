package org.BYolivia.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "AIRPLANE")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regno")
    private Integer regno;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_airplanetype")
    private AirplaneType fk_airplanetype;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_airport")
    private Airport fk_airport;

    public Integer getRegno() {
        return regno;
    }

    public void setRegno(Integer regno) {
        this.regno = regno;
    }

    public AirplaneType getFk_airplanetype() {
        return fk_airplanetype;
    }

    public void setFk_airplanetype(AirplaneType fk_airplanetype) {
        this.fk_airplanetype = fk_airplanetype;
    }

    public Airport getFk_airport() {
        return fk_airport;
    }

    public void setFk_airport(Airport fk_airport) {
        this.fk_airport = fk_airport;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Airplane airplane = (Airplane) o;
        if (regno != null && airplane.regno != null) {
            return Objects.equals(regno, airplane.regno) ;
        } else {
            return Objects.equals(fk_airplanetype, airplane.fk_airplanetype) && Objects.equals(fk_airport, airplane.fk_airport);
        }
    }

    @Override
    public int hashCode() {

        if (regno != null) {
            return regno.hashCode();
        }
        return (fk_airplanetype == null && fk_airport == null)? 0: Objects.hash(fk_airplanetype, fk_airport);
    }
}