/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.sql.Timestamp;

public class BookingRequest extends ua.training.domain.Entity {
    private Timestamp checkinDt;
    private Timestamp checkoutDt;
    private User fkUser;
    private RoomRating fkRating;
    private RequestStatus fkStatus;

    public BookingRequest() {
    }

    public BookingRequest(Long requestId) {
        super(requestId);
        fkUser = new User();
        fkStatus = new RequestStatus();
        fkRating = new RoomRating();
    }

    public static BookingRequestBuilder newBuilder(Long requestId) {
        return new BookingRequest(requestId).new BookingRequestBuilder();
    }

    public class BookingRequestBuilder {

        private BookingRequestBuilder() {
        }

        public BookingRequestBuilder setUserId(Long requestId) {
            BookingRequest.this.fkUser.setId(requestId);
            return this;
        }

        public BookingRequestBuilder setCheckInDt(Timestamp checkInDt) {
            BookingRequest.this.checkinDt = checkInDt;
            return this;
        }

        public BookingRequestBuilder setCheckOutDt(Timestamp checkOutDt) {
            BookingRequest.this.checkoutDt = checkOutDt;
            return this;
        }

        public BookingRequestBuilder setFkRating(Long fkRating) {
            BookingRequest.this.fkRating.setId(fkRating);
            return this;
        }

        public BookingRequestBuilder setFkStatus(Long fkStatus) {
            BookingRequest.this.fkStatus.setId(fkStatus);
            return this;
        }

        public BookingRequest build() {
            return BookingRequest.this;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        BookingRequest other = (BookingRequest) obj;
        if(getId() != other.getId())
            return false;
        if(!checkinDt.equals(other.getCheckinDt()))
            return false;
        if(!checkoutDt.equals(other.getCheckoutDt()))
            return false;
        return fkUser.equals(other.getFkUser())
                && fkRating.equals(other.getFkRating())
                && fkStatus.equals(other.getFkStatus());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + checkinDt.hashCode() + checkoutDt.hashCode()+ fkStatus.getId()
                + fkRating.getId() + fkUser.getId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " check in Date:" + checkinDt +
                ", check out Date:" + checkoutDt;
    }

    public Timestamp getCheckinDt() {
        return checkinDt;
    }

    public Timestamp getCheckoutDt() {
        return checkoutDt;
    }

    public User getFkUser() {
        return fkUser;
    }

    public RoomRating getFkRating() {
        return fkRating;
    }

    public RequestStatus getFkStatus() {
        return fkStatus;
    }

    public void setUserId(Long requestId) {
        this.fkUser.setId(requestId);
    }

    public void setCheckInDt(Timestamp checkInDt) {
        this.checkinDt = checkInDt;
    }

    public void setCheckOutDt(Timestamp checkOutDt) {
        this.checkoutDt = checkOutDt;
    }

    public void setFkRating(RoomRating fkRating) {
        this.fkRating = fkRating;
    }

    public void setFkStatus(RequestStatus fkStatus) {
        this.fkStatus = fkStatus;
    }

    public void setFkStatus(Long fkStatus) {
        this.fkStatus.setId(fkStatus);
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

}
