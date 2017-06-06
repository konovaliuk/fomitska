/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

public class RoomRequest extends Entity {
    private BookingRequest fkBookingrequest;
    private Long bedAmt;
    private BedType fkBedtype;
    private boolean extraBed;

    public RoomRequest() {
    }

    public RoomRequest(Long requestId) {
        super(requestId);
    }

    public RoomRequest(Long requestId, Long fkBookingrequest,Long bedAmt, Long fkBedtype, boolean extraBed) {
        this(requestId);
        this.fkBookingrequest = new BookingRequest(fkBookingrequest);
        this.bedAmt = bedAmt;
        this.fkBedtype = new BedType(fkBedtype);
        this.extraBed = extraBed;
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        RoomRequest other = (RoomRequest) obj;
        if(getId() != other.getId())
            return false;
        if(!fkBookingrequest.equals(other.getFkBookingrequest()))
            return false;
        if(!fkBedtype.equals(other.getFkBedtype()))
            return false;
        return bedAmt == other.getBedAmt() && extraBed == other.isExtraBed();
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + fkBedtype.hashCode() + fkBookingrequest.hashCode() + bedAmt);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " booking request #:" + fkBookingrequest +
                ", bedtype:" + fkBedtype + ", extra bed:" + extraBed;
    }
    public BookingRequest getFkBookingrequest() {
        return fkBookingrequest;
    }

    public void setFkBookingrequest(BookingRequest fkBookingrequest) {
        this.fkBookingrequest = fkBookingrequest;
    }

    public Long getBedAmt() {
        return bedAmt;
    }

    public void setBedAmt(Long bedAmt) {
        this.bedAmt = bedAmt;
    }

    public BedType getFkBedtype() {
        return fkBedtype;
    }

    public void setFkBedtype(BedType fkBedtype) {
        this.fkBedtype = fkBedtype;
    }

    public boolean isExtraBed() {
        return extraBed;
    }

    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

}
