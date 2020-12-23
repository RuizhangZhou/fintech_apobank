package de.rwth.swc.lab.ws2021.daifu.zelda.productivedataservice.models;

import org.springframework.data.annotation.Id;

public class CustomerAdvertisementData {
    @Id
    private String id;
    /*
        customer number and id of the product that is advertised by the campaign should identify each inputData instance
     */
    private Integer customerNumber;
    private Integer productId;

    private InputData inputData;

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public InputData getInputData() {
        return inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }
}
