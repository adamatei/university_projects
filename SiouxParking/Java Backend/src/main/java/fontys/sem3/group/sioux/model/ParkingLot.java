package fontys.sem3.group.sioux.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fontys.sem3.group.sioux.controller.AddressController;

import javax.persistence.*;

@Entity
@Table(name ="parkinglot")
public class ParkingLot {

    //unique identifier id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parkinglotId;

    //name
    @Column(name = "parkinglotName")
    private String parkinglotName;

    //max capacity of the parking lot
    @Column(name = "maxCapacity")
    private int maxCapacity;

    //the current capacity to be updated continuously
    @Column(name = "currentCapacity")
    private int currentCapacity;

    //address - unidirectional one to one
    //note - parking can have a different address than the location it is linked to
    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    //location - the location(eg office building) the parking lot is linked to
    //One location per parkinglot - many parking lots per location
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "locationId")
    private Location location;

    //default empty constructor necessary for JPA
    public ParkingLot() {
    }

    public ParkingLot(Long parkinglotId, String parkinglotName, int maxCapacity, int currentCapacity, Address address, Location location) {
        this.parkinglotId = parkinglotId;
        this.parkinglotName = parkinglotName;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.address = address;
        this.location = location;
    }

    public Long getParkinglotId() {
        return parkinglotId;
    }

    public String getParkinglotName() {
        return parkinglotName;
    }

    public void setParkinglotName(String parkinglotName) {
        this.parkinglotName = parkinglotName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
