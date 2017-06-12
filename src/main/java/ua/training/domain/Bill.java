/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Bill extends ua.training.domain.Entity {
    private BigDecimal subtotal;
    private Short discountPercentage;
    private Timestamp creationDt;
    private User fkUser;
    private BookingRequest fkBookingrequest;

    public Bill() {
    }

    public Bill(Long billId) {
        super(billId);
    }

    public Bill(Long billId, BigDecimal subtotal, Short discountPercentage, Timestamp creationDt,
    Long userId, Long id) {
        this(billId);
        this.subtotal = subtotal;
        this.discountPercentage = discountPercentage;
        this.creationDt = creationDt;
        fkUser = new User(userId);
        fkBookingrequest = new BookingRequest(id);
    }

    public Bill(Long billId, Long userId, Long fkBookingrequest, BigDecimal subtotal,
                Short discountPercentage, Timestamp creationDt) {
        this(billId);
        this.subtotal = subtotal;
        this.discountPercentage = discountPercentage;
        this.creationDt = creationDt;
        fkUser = new User(userId);
        this.fkBookingrequest = new BookingRequest(fkBookingrequest);
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
        if(!subtotal.equals(other.getSubtotal()))
                return false;
        if(discountPercentage != other.getDiscountPercentage())
            return false;
        if(!creationDt.equals(other.getCreationDt()))
            return false;
        return fkUser.equals(other.getFkUser()) && fkBookingrequest.equals(other.getFkBookingrequest());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + subtotal.intValue() + discountPercentage +
                ((creationDt == null) ? 0 : creationDt.hashCode()) + fkUser.getId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() +
                " subtotal:" + subtotal + " discount:" + discountPercentage + " date bill created:" +
                creationDt;
    }

    public BookingRequest getFkBookingrequest() {
        return fkBookingrequest;
    }

    public void setFkBookingrequest(BookingRequest fkBookingrequest) {
        this.fkBookingrequest = fkBookingrequest;
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

}
