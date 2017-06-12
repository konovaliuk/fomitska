package ua.training.service;

import ua.training.domain.BookingRequest;
import ua.training.domain.Reservation;
import ua.training.domain.RoomRequest;

import java.io.Serializable;
import java.util.List;

public class ReservationWrapper implements Serializable {
    private Long requestId;
    private BookingRequest bookingRequest;
    private List<Reservation> reservations;

    public ReservationWrapper() {

    }

    public ReservationWrapper(Long id, BookingRequest bookingRequest, List<Reservation> reservations) {
        requestId = id;
        this.bookingRequest = bookingRequest;
        this.reservations = reservations;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public BookingRequest getBookingRequest() {
        return bookingRequest;
    }

    public void setBookingRequest(BookingRequest bookingRequest) {
        this.bookingRequest = bookingRequest;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
