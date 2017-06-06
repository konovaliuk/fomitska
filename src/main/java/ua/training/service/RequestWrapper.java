package ua.training.service;

import ua.training.domain.BookingRequest;
import ua.training.domain.RoomRequest;

import java.io.Serializable;
import java.util.List;

public class RequestWrapper implements Serializable {
    private Long requestId;
    private BookingRequest bookingRequest;
    private List<RoomRequest> roomRequests;

    public RequestWrapper() {

    }

    public RequestWrapper(Long id, BookingRequest bookingRequest, List<RoomRequest> roomRequests) {
        requestId = id;
        this.bookingRequest = bookingRequest;
        this.roomRequests = roomRequests;
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

    public List<RoomRequest> getRoomRequests() {
        return roomRequests;
    }

    public void setRoomRequests(List<RoomRequest> roomRequests) {
        this.roomRequests = roomRequests;
    }
}
