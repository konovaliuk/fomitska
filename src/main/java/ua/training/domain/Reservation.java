/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

public class Reservation extends Entity {
    private RoomRequest fkRoomrequest;
    private Room fkRoom;

    public Reservation() {
        fkRoomrequest = new RoomRequest();
        fkRoom = new Room();
    }

    public Reservation(Long reservationId) {
        super(reservationId);
    }

    public Reservation(Long reservationId, Long fkRequest, Long fkRoom) {
        this(reservationId);
       this.fkRoomrequest = new RoomRequest(fkRequest);
       this.fkRoom = new Room(fkRoom);
    }

    public Reservation( Long fkRequest, Long fkRoom) {
        this.fkRoomrequest = new RoomRequest(fkRequest);
        this.fkRoom = new Room(fkRoom);
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        if(getId() != other.getId())
            return false;
        return fkRoomrequest.getId() == other.getFkRoomrequest().getId()
                && fkRoom.getId() == other.getFkRoom().getId();
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + fkRoomrequest.getId() + fkRoom.getId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " Request #" + fkRoomrequest.getId()
                + " room id: " + fkRoom.getId();
    }

    public RoomRequest getFkRoomrequest() {
        return fkRoomrequest;
    }

    public void setFkRoomrequest(RoomRequest fkRequest) {
        this.fkRoomrequest = fkRequest;
    }

    public void setFkRoomrequest(Long fkRequest) {
        this.fkRoomrequest = new RoomRequest(fkRequest);
    }

    public Room getFkRoom() {
        return fkRoom;
    }

    public void setFkRoom(Long fkRoom) {
        this.fkRoom.setId(fkRoom);
    }


    public void setFkRoom(Room fkRoom) {
        this.fkRoom = fkRoom;
    }

}
