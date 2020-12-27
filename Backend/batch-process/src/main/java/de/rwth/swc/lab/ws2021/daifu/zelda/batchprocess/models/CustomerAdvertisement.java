package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;

import de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models.enums.CustomerAdvertisementStatus;
import java.time.LocalDate;

public class CustomerAdvertisement {

    private AdvertisementCampaign advertisementCampaign;

    private LocalDate lastDisplayDate;

    private Integer numberOfTimesDisplayed;

    private CustomerAdvertisementStatus status;

    public LocalDate getLastDisplayDate() {
        return lastDisplayDate;
    }

    public void setLastDisplayDate(LocalDate lastDisplayDate) {
        this.lastDisplayDate = lastDisplayDate;
    }

    public Integer getNumberOfTimesDisplayed() {
        return numberOfTimesDisplayed;
    }

    public void setNumberOfTimesDisplayed(Integer numberOfTimesDisplayed) {
        this.numberOfTimesDisplayed = numberOfTimesDisplayed;
    }

    public CustomerAdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerAdvertisementStatus status) {
        this.status = status;
    }
}
