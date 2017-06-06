/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.domain;

import java.sql.Timestamp;

public class User extends ua.training.domain.Entity{
    private String firstName;
    private String lastName;
    private String login;
    private Timestamp birthDt;
    private String mobileNumber;
    private String email;
    private String city;
    private String street;
    private String house;
    private int appartment;
    private boolean active;
    private UserType fkUsertype;

    public User() {
    }

    public User(Long userId) {
        super(userId);
    }

    public static UserBuilder newBuilder(Long userId) {
        return new User(userId).new UserBuilder();
    }

    public class UserBuilder {

        private UserBuilder() {
        }

        public UserBuilder setUserId(Long userId) {
            User.this.setId(userId);
            return this;
        }

        public UserBuilder setFkUsertype(Long fkUsertype) {
            User.this.fkUsertype = new UserType(fkUsertype);
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public UserBuilder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public UserBuilder setBirthDt(Timestamp birthDt) {
            User.this.birthDt = birthDt;
            return this;
        }

        public UserBuilder setMobileNumber(String mobileNumber) {
            User.this.mobileNumber = mobileNumber;
            return this;
        }

        public UserBuilder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder setCity(String city) {
            User.this.city = city;
            return this;
        }

        public UserBuilder setStreet(String street) {
            User.this.street = street;
            return this;
        }

        public UserBuilder setHouse(String house) {
            User.this.house = house;
            return this;
        }

        public UserBuilder setAppartment(int appartment) {
            User.this.appartment = appartment;
            return this;
        }

        public UserBuilder setActive(int active) {
            User.this.active = active != 0;
            return this;
        }

        public User build() {
            return User.this;
        }

    }
    @Override
    public boolean equals(Object obj) {
        if(this== obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if(getId() != other.getId())
            return false;
        if(!login.equals(other.getLogin()))
            return false;
        if(!email.equals(other.getEmail()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (int)(31 * getId() + firstName.hashCode() + lastName.hashCode() + login.hashCode() + birthDt.hashCode() +
            mobileNumber.hashCode() + city.hashCode() + street.hashCode() + appartment);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@name id:" + getId() + ", login: " + login +
                ", firstName: " + firstName + ", lastName: " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public Timestamp getBirthDt() {
        return birthDt;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public int getAppartment() {
        return appartment;
    }

    public int getActive() {
        return active == true ? 1 : 0;
    }

    public void setActive(int active) {
        User.this.active = active != 0;
    }

    public UserType getFkUsertype() {
        return fkUsertype;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setBirthDt(Timestamp birthDt) {
        this.birthDt = birthDt;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setAppartment(int appartment) {
        this.appartment = appartment;
    }

    public void setFkUsertype(UserType fkUsertype) {
        this.fkUsertype = fkUsertype;
    }

}
