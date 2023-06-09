package org.medox.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "origin")

    private String origin;
    @Column(name = "destination")

    private String destination;

    @Column(name = "departure_time")

    private LocalDateTime departureTime;
    @Column(name = "price")

    private BigDecimal price;
    @Column(name = "seats_available")

    private Integer seatsAvailable;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User driver;
    @ManyToMany
    @JoinTable(name = "trip_passengers", joinColumns = @JoinColumn(name = "id_trip")
            , inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> passengers;
    @OneToMany
    @JoinTable(name = "trip_reviews", joinColumns = @JoinColumn(name = "id_trip")
            , inverseJoinColumns = @JoinColumn(name = "id_review"))
    private List<Review> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Trip trip = (Trip) o;
        return Objects.equals(id, trip.id);
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Trip(Long id, String origin, String destination, LocalDateTime departureTime, BigDecimal price, Integer seatsAvailable, User driver, List<User> passengers, List<Review> reviews) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
        this.driver = driver;
        this.passengers = passengers;
        this.reviews = reviews;
    }

    public Trip() {
    }
}
