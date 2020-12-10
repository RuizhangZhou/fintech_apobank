package de.rwth.swc.lab.ws2021.daifu.businesslogic.sqlapiconsume;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private Integer id;
    private Integer customerNumber;
    private String firstName;
    private String lastName;
    //private Float monthlyIncome;
    //private LocalDate birthday;
    //private Integer numberOfChildren;

    //private Address address;
    //private Job job;


    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
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

    @Override
    public String toString() {
        return "The customer is " + firstName + " " + lastName;
    }
}
