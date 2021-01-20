package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.productiveData;

public class LoginData {
    private Integer customerNumber;
    private String password;

    public LoginData(Integer customerNumber, String password) {
        this.customerNumber = customerNumber;
        this.password = password;
    }

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
