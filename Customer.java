package com.example.lab4;

public class Customer {

    private long customerId;
    private String name;
    private String phone;
    private String gender;

    // Constructor
    public Customer(long customerId, String name, String phone, String gender) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    // Getter methods
    public long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mCustomerId=" + customerId +
                ", mName='" + name + '\'' +
                ", mPhone='" + phone + '\'' +
                ", mGender='" + gender + '\'' +
                '}';
    }
}
