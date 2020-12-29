package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;


public class CustomerAdvertisementKey {

    private Integer customerId;

    private Integer advertisementCampaignId;

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
}
