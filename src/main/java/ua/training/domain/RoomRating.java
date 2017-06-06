/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.math.BigDecimal;
import java.util.Collection;

public class RoomRating extends ua.training.domain.Entity {
    private String ratingName;
    private BigDecimal price;
    private BigDecimal priceForExtraBed;

    public RoomRating() {
    }

    public RoomRating(Long ratingId) {
        super(ratingId);
    }

    public RoomRating(Long ratingId, String ratingName, BigDecimal price, BigDecimal priceForExtraBed) {
        this(ratingId);
        this.ratingName = ratingName;
        this.price = price;
        this.priceForExtraBed = priceForExtraBed;
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        RoomRating other = (RoomRating) obj;
        if(getId() != other.getId())
            return false;
        if (!ratingName.equals(other.getRatingName()))
            return false;
        return price.equals(other.getPrice())
                && priceForExtraBed.equals(other.getPriceForExtraBed());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + ((ratingName == null) ? 0 : ratingName.hashCode()) + price.intValue() +
            priceForExtraBed.intValue());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " standart price:" + price
                + " price for extra bed: " + priceForExtraBed;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceForExtraBed() {
        return priceForExtraBed;
    }

    public void setPriceForExtraBed(BigDecimal priceForExtraBed) {
        this.priceForExtraBed = priceForExtraBed;
    }
}
