package org.medox.business;


import org.medox.dao.TripDao;
import org.medox.model.Trip;

public class TripManager {

    public static Trip getTrip(Long id) {
        TripDao dao = new TripDao();
        Trip trip = dao.getTrip(id);
        dao.close();
        return trip;
    }

    public static Long saveTrip(Trip trip) {
        TripDao dao = new TripDao();
        dao.saveTrip(trip);
        dao.close();
        return trip.getId();
    }
}