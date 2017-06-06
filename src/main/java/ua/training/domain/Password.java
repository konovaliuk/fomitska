/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.sql.Timestamp;

public class Password extends Entity {
    private String password;
    private Timestamp expiryDt;
    private User fkUser;
    private Long attempt;

    public Password() {
    }

    public Password(Long passwordId) {
        super(passwordId);

    }

    public Password(Long passwordId, String password, Timestamp expiryDt, Long userId, Long attempt) {
        this(passwordId);
        this.password = password;
        this.expiryDt = expiryDt;
        this.attempt = attempt;
        fkUser = new User(userId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Password other = (Password) obj;
        if(getId() != other.getId())
            return false;
        if(!password.equals(other.getPassword()))
            return false;
        if(!expiryDt.equals(other.getExpiryDt()))
            return false;
        return fkUser.equals(other.getFkUser())
                && attempt == other.getAttempt();
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + password.hashCode() + expiryDt.hashCode() + attempt + fkUser.getId());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + " expiry date:" + expiryDt.toString();
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getExpiryDt() {
        return expiryDt;
    }

    public void setExpiryDt(Timestamp expiryDt) {
        this.expiryDt = expiryDt;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

    public Long getAttempt() {
        return attempt;
    }

    public void setAttempt(Long attempt) {
        this.attempt = attempt;
    }

}
