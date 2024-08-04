package pl.ekulka.ecommerce.sales.reservation.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ClientDetails {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "id")
    private List<Reservation> reservations;

    public ClientDetails() {

    }

    public ClientDetails(String customerId, String firstName, String lastName, String email) {

        this.id = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getCustomerId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
