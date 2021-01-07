package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models.enums.AdvertismentStatus;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerAdvertisement {
    private CustomerAdvertisementKey id = new CustomerAdvertisementKey();
    private Customer customer;
    //private AdvertisementCampaign advertisementCampaign;
    private LocalDate lastDisplayDate;
    private Integer numberOfTimesDisplayed;
    private AdvertismentStatus status;

    public CustomerAdvertisementKey getId() {
        return id;
    }

    public void setId(CustomerAdvertisementKey id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public AdvertismentStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertismentStatus status) {
        this.status = status;
    }
}
