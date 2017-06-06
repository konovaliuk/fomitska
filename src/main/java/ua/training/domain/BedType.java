package ua.training.domain;

import java.math.BigDecimal;
import java.util.Collection;

public class BedType extends ua.training.domain.Entity {
    private String type;
    private BigDecimal pricePlusAmt;

    public BedType() {
    }

    public BedType(Long id) {
        super(id);
    }

    public BedType(Long id, String type, BigDecimal pricePlusAmt) {
        this(id);
        this.type = type;
        this.pricePlusAmt = pricePlusAmt;
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        BedType other = (BedType) obj;
        if(pricePlusAmt != other.getPricePlusAmt())
            return false;
        if(getId() != other.getId())
            return false;
        return type.equals(other.getType());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + pricePlusAmt.intValue() + ((type == null) ? 0 : type.hashCode()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name type: " + type + " id:" + getId() + " extra price:" + pricePlusAmt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPricePlusAmt() {
        return pricePlusAmt;
    }

    public void setPricePlusAmt(BigDecimal pricePlusAmt) {
        this.pricePlusAmt = pricePlusAmt;
    }

}
