package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;

public class CustomerAdvertisementData {

    private CustomerAdvertisementKey key;
    private InputData inputData;


    public CustomerAdvertisementData(){}

    public CustomerAdvertisementData(CustomerAdvertisementKey key, InputData inputData){
        this.key = key;
        this.inputData = inputData;
    }

    public CustomerAdvertisementKey getKey() {
        return key;
    }

    public void setKey(CustomerAdvertisementKey key) {
        this.key = key;
    }

    public InputData getInputData() {
        return inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }
}
