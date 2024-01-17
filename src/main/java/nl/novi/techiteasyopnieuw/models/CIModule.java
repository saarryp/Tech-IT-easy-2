package nl.novi.techiteasyopnieuw.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "CIModule")
public class CIModule {
    @Id
    @GeneratedValue

    private Long id;
    private String name;
    private String type;
    private Double price;

    public CIModule(Long id, String name, String type, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    @OneToMany(mappedBy = "ciModule")
    private Set<Television>televisions = new HashSet<>();

    //default constructor maken iggv dat er niks instaat, eis van Java
    public CIModule(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




}
