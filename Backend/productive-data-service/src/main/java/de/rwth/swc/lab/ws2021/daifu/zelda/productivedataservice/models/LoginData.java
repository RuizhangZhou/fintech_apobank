package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models;

import org.springframework.data.annotation.Id;

public class LoginData {
    @Id
    private String id;

    private Integer customerNumber;
    private String password;

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
