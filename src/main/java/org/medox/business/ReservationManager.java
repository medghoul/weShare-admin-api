package org.medox.business;

import org.medox.dao.ReservationDao;
import org.medox.model.Reservation;


public class ReservationManager {
    public static Reservation getReservation(Long id){
        ReservationDao dao=new ReservationDao();
        Reservation reservation=dao.getReservation(id);
        dao.close();
        return reservation;
    }
    public static Long saveReservation(Reservation reservation) {
        ReservationDao dao = new ReservationDao();
        dao.saveReservation(reservation);
        dao.close();
        return reservation.getId();
    }
}
