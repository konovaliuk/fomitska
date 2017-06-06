/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Bill extends ua.training.domain.Entity {
    private long invoiceNumber;
    private BigDecimal subtotal;
    private Short discountPercentage;
    private Timestamp creationDt;
    private User fkUser;
    private Room fkRoom;

    public Bill() {
    }

    public Bill(Long billId) {
        super(billId);
    }

    public Bill(Long billId, long invoiceNumber, BigDecimal subtotal, Short discountPercentage, Timestamp creationDt,
    Long userId, Long roomId) {
        this(billId);
        this.invoiceNumber = invoiceNumber;
        this.subtotal = subtotal;
        this.discountPercentage = discountPercentage;
        this.creationDt = creationDt;
        fkUser = new User(userId);
        fkRoom = new Room(roomId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Bill other = (Bill) obj;
        if(getId() != other.getId())
            return false;
        if(invoiceNumber != other.getInvoiceNumber())
            return false;
        if(!subtotal.equals(other.getSubtotal()))
                return false;
        if(discountPercentage != other.getDiscountPercentage())
            return false;
        if(!creationDt.equals(other.getCreationDt()))
            return false;
        return fkUser.equals(other.getFkUser()) && fkRoom.equals(other.getFkRoom());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + invoiceNumber + subtotal.intValue() + discountPercentage +
                ((creationDt == null) ? 0 : creationDt.hashCode()) + fkUser.getId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " extra invoiceNumber:" + invoiceNumber +
                " subtotal:" + subtotal + " discount:" + discountPercentage + " date bill created:" +
                creationDt;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Short getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Short discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Timestamp getCreationDt() {
        return creationDt;
    }

    public void setCreationDt(Timestamp creationDt) {
        this.creationDt = creationDt;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

    public Room getFkRoom() {
        return fkRoom;
    }

    public void setFkRoom(Room fkRoom) {
        this.fkRoom = fkRoom;
    }

}
