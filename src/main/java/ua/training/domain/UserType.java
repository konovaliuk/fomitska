/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

public class UserType extends ua.training.domain.Entity {
    private String typeName;

    public UserType() {
    }

    public UserType(Long usertypeId) {
        super(usertypeId);
    }

    public UserType(Long usertypeId, String typeName) {
        this(usertypeId);
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        UserType other = (UserType) obj;
        if(getId() != other.getId())
            return false;
        return typeName.equals(other.getTypeName());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + ((typeName == null) ? 0 : typeName.hashCode()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name type: " + typeName + " id:" + getId();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
