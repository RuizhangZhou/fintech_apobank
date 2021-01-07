package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;

public class CustomerAdvertisementData {
    /*
        customerId and advertisementCampaignId identify each inputData instance
     */
    private Integer customerId;
    private Integer advertisementCampaignId;

    private InputData inputData;

    public CustomerAdvertisementData(Integer customerId, Integer advertisementCampaignId, InputData inputData) {
        this.customerId = customerId;
        this.advertisementCampaignId = advertisementCampaignId;
        this.inputData = inputData;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAdvertisementCampaignId() {
        return advertisementCampaignId;
    }

    public void setAdvertisementCampaignId(Integer advertisementCampaignId) {
        this.advertisementCampaignId = advertisementCampaignId;
    }

    public InputData getInputData() {
        return inputData;
    }

    public void setInputData(InputData inputData) {
        this.inputData = inputData;
    }
}
