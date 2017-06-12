/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

public class Room extends ua.training.domain.Entity {
    private long roomNumber;
    private long square;
    private long floor;
    private boolean cityView;
    private boolean balcony;
    private boolean extraBed;
    private BedType fkBedtype;
    private RoomRating fkRating;

    public Room() {
    }

    public Room(Long id) {
        super(id);
    }

    public static RoomBuilder newBuilder(Long roomId) {
        return new Room(roomId).new RoomBuilder();
    }

    public class RoomBuilder {

        private RoomBuilder() {
        }

        public RoomBuilder setRoomId(Long roomId) {
            Room.this.setId(roomId);
            return this;
        }

        public RoomBuilder setRoomNumber(long roomNumber) {
            Room.this.roomNumber = roomNumber;
            return this;
        }

        public RoomBuilder setAvailable(int available) {
            return this;
        }

        public RoomBuilder setSquare(long square) {
            Room.this.square = square;
            return this;
        }

        public RoomBuilder setFloor(long floor) {
            Room.this.floor = floor;
            return this;
        }
        public RoomBuilder setFkRating(Long fkRating) {
            Room.this.fkRating = new RoomRating(fkRating);
            return this;
        }

        public RoomBuilder setCityView(int cityView) {
            Room.this.cityView = cityView != 0;
            return this;
        }

        public RoomBuilder setBalcony(int balcony) {
            Room.this.balcony = balcony != 0;
            return this;
        }

        public RoomBuilder setExtraBed(int extraBed) {
            Room.this.extraBed = extraBed != 0;
            return this;
        }

        public RoomBuilder setFkBedType(Long fkBedType) {
            Room.this.fkBedtype = new BedType(fkBedType);
            return this;
        }

        public Room build() {
            return Room.this;
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
        Room other = (Room) obj;
        if(getId() != other.getId())
            return false;
        if(roomNumber != other.getRoomNumber())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + roomNumber + square + floor + fkBedtype.getId() + fkRating.getId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " room #:" + roomNumber +
                " square:" + square + " floor:" + floor;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public long getSquare() {
        return square;
    }

    public long getFloor() {
        return floor;
    }

    public boolean isCityView() {
        return cityView;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public BedType getFkBedtype() {
        return fkBedtype;
    }

    public RoomRating getFkRating() {
        return fkRating;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setSquare(long square) {
        this.square = square;
    }

    public void setFloor(long floor) {
        this.floor = floor;
    }

    public void setCityView(boolean cityView) {
        this.cityView = cityView;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    public void setFkBedtype(BedType fkBedtype) {
        this.fkBedtype = fkBedtype;
    }

    public void setFkRating(RoomRating fkRating) {
        this.fkRating = fkRating;
    }

}
