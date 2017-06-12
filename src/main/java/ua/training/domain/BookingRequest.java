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

    public static BookingRequestBuilder newBuilder() {
        return new BookingRequest().new BookingRequestBuilder();
    }

    public class BookingRequestBuilder {

        private BookingRequestBuilder() {
        }

        public BookingRequestBuilder setUserId(Long requestId) {
            BookingRequest.this.fkUser.setId(requestId);
            return this;
        }

        public BookingRequestBuilder setFkUser(User user) {
            BookingRequest.this.fkUser = user;
            return this;
        }

        public BookingRequestBuilder setCheckinDt(Timestamp checkinDt) {
            BookingRequest.this.checkinDt = checkinDt;
            return this;
        }

        public BookingRequestBuilder setCheckoutDt(Timestamp checkoutDt) {
            BookingRequest.this.checkoutDt = checkoutDt;
            return this;
        }

        public BookingRequestBuilder setFkRating(Long fkRating) {
            BookingRequest.this.fkRating = new RoomRating(fkRating);
            return this;
        }

        public BookingRequestBuilder setFkStatus(Long fkStatus) {
            BookingRequest.this.fkStatus = new RequestStatus(fkStatus);
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

    public void setCheckinDt(Timestamp checkInDt) {
        this.checkinDt = checkInDt;
    }

    public void setCheckoutDt(Timestamp checkOutDt) {
        this.checkoutDt = checkOutDt;
    }

    public void setFkRating(RoomRating fkRating) {
        this.fkRating = fkRating;
    }

    public void setFkStatus(RequestStatus fkStatus) {
        this.fkStatus = fkStatus;
    }

    public void setFkStatus(Long fkStatus) {
        this.fkStatus = new RequestStatus(fkStatus);
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

}
