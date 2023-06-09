package org.medox.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "reservation")

public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "reservation_date")
  private LocalDateTime reservationDate;
  @ManyToOne
  @JoinColumn(name = "id_trip")
  private Trip trip;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reservation that = (Reservation) o;
    return Objects.equals(id, that.id);
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

  public LocalDateTime getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(LocalDateTime reservationDate) {
    this.reservationDate = reservationDate;
  }

  public Trip getTrip() {
    return trip;
  }

  public void setTrip(Trip trip) {
    this.trip = trip;
  }

  public Reservation(Long id, LocalDateTime reservationDate, Trip trip) {
    this.id = id;
    this.reservationDate = reservationDate;
    this.trip = trip;
  }

  public Reservation() {
  }
}
