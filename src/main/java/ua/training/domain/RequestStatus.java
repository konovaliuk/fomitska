/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.util.Collection;

public class RequestStatus extends ua.training.domain.Entity {
    private String description;

    public RequestStatus() {
    }

    public RequestStatus(Long statusId) {
        super(statusId);
    }

    public RequestStatus(Long statusId, String description) {
        this(statusId);
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        RequestStatus other = (RequestStatus) obj;
        if(getId() != other.getId())
            return false;
        return description.equals(other.getDescription());
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + ((description == null) ? 0 : description.hashCode()));
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " description: " + description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
