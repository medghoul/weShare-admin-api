package org.medox.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@NamedQueries({
        @NamedQuery(name = "UserByEmailPassword",query="select U from org.medox.model.User U where U.email=:email and U.password=:password"),
        @NamedQuery(name = "FindUserByEmail",query="select U from org.medox.model.User U where U.email=:email"),

})

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")

    private String lastName;
    @Column(name = "email",nullable = false,unique = true)

    private String email;
    @Column(name = "password",nullable = false)

    private String password;
    @Column(name = "phone_number",unique = true)

    private String phoneNumber;
    @Column(name = "rating")
    private Double rating;
    @OneToMany
    @JoinTable(name = "driver_trip", joinColumns = @JoinColumn(name = "id_user")
            , inverseJoinColumns = @JoinColumn(name = "id_trip"))
    private List<Trip> trips;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "passenger_reservation", joinColumns = @JoinColumn(name = "id_user")
            , inverseJoinColumns = @JoinColumn(name = "id_reservation"))
    private List<Reservation> reservations;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public User(Long id, String firstName, String lastName, String email, String password, String phoneNumber, Double rating, List<Trip> trips, List<Reservation> reservations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.trips = trips;
        this.reservations = reservations;
    }

    public User() {
    }
}
