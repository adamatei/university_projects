package fontys.sem3.it.ticketstore.model;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity

public class Concert {

        // unique id to identify a concert
        @Id
        @GeneratedValue
        private int id;

        // setting up the details about the concert
        private String location;
        private String country;
        private String city;
        private Date dateTime;


        // constructors
        public Concert(int id, String location, String country, String city, Date dateTime) {
            this.id = id;
            this.location = location;
            this.country = country;
            this.city = city;
            this.dateTime = dateTime;
        }

        public Concert(String location, String country, String city, Date dateTime) {
            this.location = location;
            this.country = country;
            this.city = city;
            this.dateTime = dateTime;
        }

        public Concert() {
        }

        // setters and getters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }

}
