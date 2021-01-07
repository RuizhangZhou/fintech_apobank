package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementCampaign {

    private Integer id;
    Set<CustomerAdvertisement> customerAdvertisements;
    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CustomerAdvertisement> getCustomerAdvertisements() {
        return customerAdvertisements;
    }

    public void setCustomerAdvertisements(Set<CustomerAdvertisement> customerAdvertisements) {
        this.customerAdvertisements = customerAdvertisements;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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
}
