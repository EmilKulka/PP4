package pl.ekulka.ecommerce.sales.reservation.model;

import jakarta.persistence.*;
import pl.ekulka.ecommerce.sales.reservation.model.Reservation;

import java.util.List;

@Entity
@Table(name = "Client_Details")
public class ClientDetails {
    @Id
    @Column(name = "customer_id")
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "customerId")
    private List<Reservation> reservations;

    public ClientDetails() {

    }

    public ClientDetails(String customerId, String firstName, String lastName, String email) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
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
