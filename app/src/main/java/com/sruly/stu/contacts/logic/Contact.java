package com.sruly.stu.contacts.logic;

import android.support.annotation.NonNull;

/**
 * Created by stu on 4/30/2018.
 *
 */

public class Contact implements Comparable<Contact>{
    String firstName, lastName, id;
    int birthYear;

    public Contact(String firstName, String lastName, String id, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.birthYear = birthYear;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge(int currentYear){
        return currentYear - birthYear;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }

    @Override
    public int compareTo(@NonNull Contact o) {
        return 0;
    }
}
