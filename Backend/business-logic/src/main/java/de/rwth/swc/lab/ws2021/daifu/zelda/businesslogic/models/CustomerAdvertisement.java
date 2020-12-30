package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.AdvertismentStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAdvertisement {
    private AdvertismentCampaign advertismentCampaign;
    private Customer costumer;
    private String lastDisplayDate;
    private Integer numberOfTimesDisplayed;
    private AdvertismentStatus status;

    public AdvertismentCampaign getAdvertismentCampaign() {
        return advertismentCampaign;
    }

    public void setAdvertismentCampaign(AdvertismentCampaign advertismentCampaign) {
        this.advertismentCampaign = advertismentCampaign;
    }

    public Customer getCostumer() {
        return costumer;
    }

    public void setCostumer(Customer costumer) {
        this.costumer = costumer;
    }

    public String getLastDisplayDate() {
        return lastDisplayDate;
    }

    public void setLastDisplayDate(String lastDisplayDate) {
        this.lastDisplayDate = lastDisplayDate;
    }

    public Integer getNumberOfTimesDisplayed() {
        return numberOfTimesDisplayed;
    }

    public void setNumberOfTimesDisplayed(Integer numberOfTimesDisplayed) {
        this.numberOfTimesDisplayed = numberOfTimesDisplayed;
    }

    public AdvertismentStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertismentStatus status) {
        this.status = status;
    }
}
