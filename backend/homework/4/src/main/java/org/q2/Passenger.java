package org.q2;

public class Passenger {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;
    private final String travelClass;
    private final String confirmationNumber;

    public Passenger(String firstName, String lastName, int age, String gender, String travelClass,
                     String confirmationNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.travelClass = travelClass;
        this.confirmationNumber = confirmationNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getTravelClass() {
        return travelClass;
    }
    public String getConfirmationNumber() {
        return confirmationNumber;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((confirmationNumber == null) ? 0 : confirmationNumber.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Passenger other = (Passenger) obj;
        if (confirmationNumber == null) {
            return other.confirmationNumber == null;
        } else return confirmationNumber.equals(other.confirmationNumber);
    }

}