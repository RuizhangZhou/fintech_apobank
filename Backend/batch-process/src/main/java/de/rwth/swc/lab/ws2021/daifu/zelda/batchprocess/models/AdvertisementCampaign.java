package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess.models;

import java.time.LocalDate;
import java.util.Set;

public class AdvertisementCampaign {

    private Integer id;

    private Set<CustomerAdvertisement> customerAdvertisements;

    private LocalDate startDate;

    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<CustomerAdvertisement> getCustomerAdvertisements() {
        return customerAdvertisements;
    }

    public void setCustomerAdvertisements(Set<CustomerAdvertisement> customerAdvertisements) {
        this.customerAdvertisements = customerAdvertisements;
    }
}
