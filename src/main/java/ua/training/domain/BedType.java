package ua.training.domain;

public class BedType extends ua.training.domain.Entity {
    private String type;

    public BedType() {
    }

    public BedType(Long id) {
        super(id);
    }

    public BedType(Long id, String type) {
        this(id);
        this.type = type;
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
        if(getId() != other.getId())
            return false;
        return type.equals(other.getType());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId()  + ((type == null) ? 0 : type.hashCode()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name type: " + type + " id:" + getId() ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
