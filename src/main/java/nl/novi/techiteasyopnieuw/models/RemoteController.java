package nl.novi.techiteasyopnieuw.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Remote Controller")
public class RemoteController {

    @Id
    @GeneratedValue
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    public RemoteController(Long id, String compatibleWith, String batteryType, String name, String brand, Double price, Integer originalStock) {
        this.id = id;
        this.compatibleWith = compatibleWith;
        this.batteryType = batteryType;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.originalStock = originalStock;
    }

    public RemoteController(){

    }
//de televisionclass is de eigenaar dus moet MappedBy toegevoegd worden
    @OneToOne(mappedBy = "remoteController")
    private Television television;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }
}
