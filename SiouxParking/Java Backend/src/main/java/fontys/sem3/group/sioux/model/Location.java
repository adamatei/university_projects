package fontys.sem3.group.sioux.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //unique identifier id
    private Long locationId;

    @Column(name = "name")
    private String name;

    //address - unidirectional one to one
    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    //parkinglots - unidirectional one to many
    //One location can have many parkinglots

    @OneToMany(mappedBy = "location")
    private List<ParkingLot> parkingLots;

    public Location() {
    }

    public Location(Long locationId, String name, Address address, List<ParkingLot> parkingLots) {
        this.locationId = locationId;
        this.name = name;
        this.address = address;
        this.parkingLots = parkingLots;
    }

    public Long getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
}
